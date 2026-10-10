package dzy.pojo;

import java.util.List;

/**
 * Created by luckf on 2022/4/30.
 */
public class Page<T>
{
    public static final int PAGE_SIZE=6;
    //目前指定的頁面
    private Integer pageNo;
    //每個page所包含的最大項目數
    private Integer pageSize=PAGE_SIZE;
    private Integer itemCount;
    private List<T> items;

    @Override
    public String toString()
    {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", itemCount=" + itemCount +
                ", items=" + items +
                '}';
    }

    public Integer getPageNo()
    {
        return pageNo;
    }
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
        if(pageSize<PAGE_SIZE) this.pageSize= Page.PAGE_SIZE;

    }

    public Integer getItemCount()
    {
        return itemCount;
    }

    /**
     *
     * @param pageTotalCount
     */
    public void setItemCount(Integer itemCount)
    {
        this.itemCount = itemCount;
    }

    /**
     *
     * @return 取得目前的總頁數
     */
    public Integer getPageCount()
    {
        Integer pageCount=itemCount/pageSize;
        if(itemCount%pageSize>0) pageCount++;
        return pageCount;
    }

    /**
     *
     * @return 取回目前暫存的items
     */
    public List<T> getItems()
    {
        return items;
    }

    /**
     *
     * @param items 存放items
     */
    public void setItems(List<T> items)
    {
        this.items = items;
    }

    /**
     *
     * @param pageNo        目前頁碼
     * @param pageSize      每頁的條目數
     * @param itemCount   目前條目總量
     */
    public Page(Integer pageNo, Integer pageSize, Integer itemCount)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        if(pageSize<PAGE_SIZE) pageSize= Page.PAGE_SIZE;
        this.itemCount=itemCount;

    }


}
