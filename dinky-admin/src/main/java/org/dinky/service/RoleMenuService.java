/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.dinky.service;

import org.dinky.data.dto.RoleMenuDto;
import org.dinky.data.model.RoleMenu;
import org.dinky.mybatis.service.ISuperService;

public interface RoleMenuService extends ISuperService<RoleMenu> {

    /**
     * Assign menu to role
     *
     * @param roleId role id
     * @param menuId menu id
     * @return boolean {@code true} if success, {@code false} if failed
     */
    boolean assignMenuToRole(Integer roleId, Integer[] menuId);

    /**
     * Query menus by role id
     *
     * @param roleId role id
     * @return {@link RoleMenuDto}
     */
    RoleMenuDto queryMenusByRoleId(Integer roleId);
}
