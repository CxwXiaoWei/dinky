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

package org.dinky.controller;

import org.dinky.annotation.Log;
import org.dinky.data.dto.RoleMenuDto;
import org.dinky.data.enums.BusinessType;
import org.dinky.data.enums.Status;
import org.dinky.data.model.Menu;
import org.dinky.data.result.ProTableResult;
import org.dinky.data.result.Result;
import org.dinky.service.MenuService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * save or update menu
     *
     * @param menu {@link Menu}
     * @return {@link Result} with {@link Void}
     */
    @PutMapping("addOrUpdate")
    @Log(title = "Insert OR Update Menu ", businessType = BusinessType.INSERT_OR_UPDATE)
    @ApiOperation("INSERT OR UPDATE Menu")
    public Result<Void> saveOrUpdateMenu(@RequestBody Menu menu) {
        if (menuService.saveOrUpdate(menu)) {
            return Result.succeed(Status.SAVE_SUCCESS);
        } else {
            return Result.failed(Status.SAVE_FAILED);
        }
    }

    /**
     * list Menus
     *
     * @param para {@link JsonNode}
     * @return {@link ProTableResult} with {@link Menu}
     */
    @GetMapping("listMenus")
    @ApiOperation("Query Menu List")
    public Result<List<Menu>> listMenus() {
        return Result.data(menuService.list());
    }

    /**
     * delete menu by id
     *
     * @param id {@link Integer}
     * @return {@link Result} of {@link Void}
     */
    @DeleteMapping("/delete")
    @ApiOperation("Delete Menu By Id")
    @Log(title = "Delete Menu By Id", businessType = BusinessType.DELETE)
    public Result<Void> deleteMenuById(@RequestParam("id") Integer id) {
        if (menuService.deleteMenuById(id)) {
            return Result.succeed(Status.DELETE_SUCCESS);
        } else {
            return Result.failed(Status.DELETE_FAILED);
        }
    }

    /**
     * load role menu tree
     *
     * @param roleId role id
     * @return {@link RoleMenuDto}
     */
    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    @ApiOperation("Load Role Menu")
    public Result<RoleMenuDto> roleMenuTreeSelect(@PathVariable("roleId") Integer roleId) {
        List<Menu> menus = menuService.list();
        RoleMenuDto menuVO =
                RoleMenuDto.builder()
                        .roleId(roleId)
                        .selectedMenuIds(menuService.selectMenuListByRoleId(roleId))
                        .menus(menus)
                        .build();
        return Result.succeed(menuVO);
    }
}
