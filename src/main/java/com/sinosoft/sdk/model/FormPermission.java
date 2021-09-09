package com.sinosoft.sdk.model;

import com.sinosoft.sdk.model.rescource.FormPermissionItem;
import lombok.Data;

import java.util.List;

@Data
public class FormPermission {
    private List<FormPermissionItem> submit;
    private List<FormPermissionItem> manageSelf;
    private List<FormPermissionItem> manageAll;
    private List<FormPermissionItem> view;

}
