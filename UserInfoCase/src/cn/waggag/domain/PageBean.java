package cn.waggag.domain;

import java.util.List;

/**
 * @description: ��ҳ����
 * @author: waggag
 * @time: 2019/7/9 16:31
 */
public class PageBean<T> {

    private int totalCount; // �ܼ�¼��
    private int totalPage ; // ��ҳ��
    private List<T> list ; // ÿҳ������
    private int currentPage ; //��ǰҳ��
    private int rows;//ÿҳ��ʾ�ļ�¼��


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
