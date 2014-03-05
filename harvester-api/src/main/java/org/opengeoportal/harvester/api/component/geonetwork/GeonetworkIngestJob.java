package org.opengeoportal.harvester.api.component.geonetwork;

import com.google.common.collect.Lists;

import org.opengeoportal.harvester.api.client.geonetwork.GeoNetworkClient;
import org.opengeoportal.harvester.api.client.geonetwork.GeoNetworkSearchParams;
import org.opengeoportal.harvester.api.client.geonetwork.GeoNetworkSearchResponse;
import org.opengeoportal.harvester.api.client.geonetwork.GeoNetworkSearchResult;
import org.opengeoportal.harvester.api.component.BaseIngestJob;
import org.opengeoportal.harvester.api.domain.IngestGeonetwork;
import org.opengeoportal.harvester.api.domain.IngestReportErrorType;
import org.opengeoportal.harvester.api.metadata.model.Metadata;
import org.opengeoportal.harvester.api.metadata.parser.MetadataParser;
import org.opengeoportal.harvester.api.metadata.parser.MetadataParserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import java.io.PrintStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;

/**
 * IngestJob that read from a remote Geonetwork.
 * 
 * @author <a href="mailto:juanluisrp@geocat.net">Juan Luis Rodríguez</a>.
 * 
 */
public class GeonetworkIngestJob extends BaseIngestJob {
	/** Logger. */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opengeoportal.harvester.api.component.BaseIngestJob#ingest()
	 */
	@Override
	public void ingest() {
		try {
			boolean processFinished = false;
			int start = 1;

            URL geonetworkURL = new URL(ingest.getActualUrl());

			GeoNetworkClient gnClient = new GeoNetworkClient(geonetworkURL);

			GeoNetworkSearchParams searchParameters = new GeoNetworkSearchParams(
					(IngestGeonetwork) ingest);
			logger.info("GeonetworkIngestJob: search parameters "
					+ searchParameters.toString());

			while (!(isInterruptRequested() || processFinished)) {
				searchParameters.setFrom(start);
				GeoNetworkSearchResponse searchResponse = gnClient
						.search(searchParameters);

				List<Metadata> metadataList = Lists
						.newArrayListWithCapacity(searchResponse
                                .getMetadataSearchResults().size());

				for (GeoNetworkSearchResult record : searchResponse
						.getMetadataSearchResults()) {

					try {
						Document document = gnClient.retrieveMetadata(record
								.getId());

						MetadataParser parser = parserProvider
								.getMetadataParser(document);
						MetadataParserResponse parserResult = parser
								.parse(document);

						Metadata metadata = parserResult.getMetadata();
						metadata.setInstitution(ingest.getNameOgpRepository());

						boolean valid = metadataValidator.validate(metadata,
                                report);
						if (valid) {
							metadataList.add(metadata);
						}

					} catch (Exception ex) {
						saveException(ex,
								IngestReportErrorType.WEB_SERVICE_ERROR);

					}
				}

				metadataIngester.ingest(metadataList, getIngestReport());

				// --- check to see if we have to perform additional searches
				processFinished = (start + searchParameters.getPageSize() > searchResponse
						.getTotal());

				start += searchParameters.getPageSize();

			}

		} catch (Exception e) {
			logger.error(
					"Error in Geonetwork Ingest: " + this.ingest.getName(), e);
			saveException(e, IngestReportErrorType.SYSTEM_ERROR);
		}

	}
}
