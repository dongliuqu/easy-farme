package com.lihao.common.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * 构造目录JSON树
 * Created by fukang on 2017/5/26 0026.
 */
public class TreeBuilder<T extends BaseTreeVO> {

    List<T> nodes = new ArrayList<>();

    public List<T> buildTree(List<T> nodes) {

        TreeBuilder<T> treeBuilder = new TreeBuilder<T>(nodes);

        return treeBuilder.buildJSONTree();
    }

    public TreeBuilder() {
    }

    public TreeBuilder(List<T> nodes) {
        super();
        this.nodes = nodes;
    }

    // 构建JSON树形结构
    public List<T> buildJSONTree() {
        List<T> nodeTree = buildTree();
        return nodeTree;
    }

    // 构建树形结构
    public List<T> buildTree() {
        List<T> treeBaseTreeVOs = new ArrayList<T>();
        List<T> rootBaseTreeVOs = getRootBaseTreeVOs();
        for (T rootBaseTreeVO : rootBaseTreeVOs) {
            buildChildBaseTreeVOs(rootBaseTreeVO);
            treeBaseTreeVOs.add(rootBaseTreeVO);
        }
        return treeBaseTreeVOs;
    }

    // 递归子节点
    public void buildChildBaseTreeVOs(T node) {
        List<T> children = getChildBaseTreeVOs(node);
        if (!children.isEmpty()) {
            for (T child : children) {
                buildChildBaseTreeVOs(child);
            }
            node.setChildren((List<BaseTreeVO>) children);
        }
    }

    // 获取父节点下所有的子节点
    public List<T> getChildBaseTreeVOs(T pnode) {
        List<T> childBaseTreeVOs = new ArrayList<T>();
        for (T n : nodes) {
            if (pnode.getId().equals(n.getPId())) {
                childBaseTreeVOs.add(n);
            }
        }
        return childBaseTreeVOs;
    }

    /**
     * 判断是否为根节点
     * 空的pid也作为根节点
     *
     * @param node
     * @return
     */
    public boolean rootBaseTreeVO(T node) {
        if (node.getPId() == null) {
            return true;
        }
        boolean isRootBaseTreeVO = true;
        for (T n : nodes) {
            if (node.getPId().equals(n.getId())) {
                isRootBaseTreeVO = false;
                break;
            }
        }
        return isRootBaseTreeVO;
    }

    // 获取集合中所有的根节点
    public List<T> getRootBaseTreeVOs() {
        List<T> rootBaseTreeVOs = new ArrayList<T>();
        for (T n : nodes) {
            if (rootBaseTreeVO(n)) {
                rootBaseTreeVOs.add(n);
            }
        }
        return rootBaseTreeVOs;
    }


}