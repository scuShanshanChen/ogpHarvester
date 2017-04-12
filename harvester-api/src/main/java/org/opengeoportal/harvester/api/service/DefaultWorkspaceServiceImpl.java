/*
 * IngestFormBean.java
 *
 * Copyright (C) 2013
 *
 * This file is part of Open Geoportal Harvester.
 *
 * This software is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this library; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 * As a special exception, if you link this library with other files to produce
 * an executable, this library does not by itself cause the resulting executable
 * to be covered by the GNU General Public License. This exception does not
 * however invalidate any other reasons why the executable file might be covered
 * by the GNU General Public License.
 *
 * Authors:: Jose García (mailto:jose.garcia@geocat.net)
 */
package org.opengeoportal.harvester.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.opengeoportal.harvester.api.dao.DefaultWorspaceRepository;
import org.opengeoportal.harvester.api.domain.DefaultWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class DefaultWorkspaceServiceImpl implements DefaultWorkspaceService {
    private Logger logger = LoggerFactory
            .getLogger(DefaultWorkspaceServiceImpl.class);

    
    @Resource
    private DefaultWorspaceRepository defaultWorkspaceRepository;

    @Override
    public void save(DefaultWorkspace entity) {
        defaultWorkspaceRepository.save(entity);

    }

    @Override
    public void delete(Long id) {
        defaultWorkspaceRepository.delete(id);

    }

    @Override
    public List<DefaultWorkspace> findAll() {

        return defaultWorkspaceRepository.findAll();
    }
    
    DefaultWorkspace findByWorksp(String worksp) {
        return null;
    }




}