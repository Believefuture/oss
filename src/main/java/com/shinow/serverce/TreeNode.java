package com.shinow.serverce;

import com.shinow.entity.TAuMenuInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/9.
 */
public class TreeNode {
    public TreeNode parent;
    private TAuMenuInfoEntity menuinfoentity;
    private List<TreeNode> children = new ArrayList<TreeNode>();
    private boolean checked;

    public TAuMenuInfoEntity getMenuinfoentity() {
        return menuinfoentity;
    }

    public void setMenuinfoentity(TAuMenuInfoEntity menuinfoentity) {
        this.menuinfoentity = menuinfoentity;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void addChild(TreeNode childNode){
        childNode.parent = this;
        children.add(childNode);
    }

}
