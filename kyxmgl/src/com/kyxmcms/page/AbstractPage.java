package com.kyxmcms.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractPage<E> implements Page<E> {

	//默认第一页
    public static final int DEFAULT_FIRST_PAGE_NUM = 1;
    //默认12条记录
    public static final int DEFAULT_PAGE_SIZE = 12;
    
    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected int pageNum = DEFAULT_FIRST_PAGE_NUM;
    
    protected List<E> items;
    protected boolean lastPage;//是最后一页？
    protected boolean firstPage;//是第一页？
    
    public int getFirstPageNum() {
        return DEFAULT_FIRST_PAGE_NUM;
    }

    public int getPageSize() {
        return pageSize;
    }
    
    public int getPageSizeMax() {
        return 8;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum < DEFAULT_FIRST_PAGE_NUM) pageNum = DEFAULT_FIRST_PAGE_NUM;
        this.pageNum = pageNum;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(Collection<E> items) {
        if (items == null) items = Collections.emptyList();
        this.items = new ArrayList<E>(items);
        //传入的数据不足，说明是最后一页
        this.lastPage = this.items.size() < this.pageSize;
        //当前页小于等于1则是第一页，防止无数据时出现第0页
        this.firstPage = (getPageNum() <= getFirstPageNum());
    }

    public boolean isFirstPage() {
    	firstPage = (getPageNum() <= getFirstPageNum());
    	return firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public int getPrevPageNum() {
        return isFirstPage() ? getFirstPageNum() : getPageNum() - 1;
    }

    public int getNextPageNum() {
        return isLastPage() ? getPageNum() : getPageNum() + 1;
    }

    public int getPageStartIndex() {
        return (getPageNum() - getFirstPageNum()) * getPageSize();
    }

    public int getPageEndIndex() {
        return getPageStartIndex() + getItems().size();
    }

    public int getPrevPageEndIndex() {
        return isFirstPage() ? 0 : getPageStartIndex() - 1;
    }

    public int getNextPageStartIndex() {
        return isLastPage() ? getPageEndIndex() : getPageEndIndex() + 1;
    }

    public Iterator<E> iterator() {
        return this.items.iterator();
    }

    @Override
    public String toString() {
        return "Page[" + this.getPageNum() + "]:" + items.toString();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
