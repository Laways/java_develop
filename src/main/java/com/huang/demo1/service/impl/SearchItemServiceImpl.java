package com.huang.demo1.service.impl;

import com.huang.demo1.common.ResultUtils;
import com.huang.demo1.common.utils.Result;
import com.huang.demo1.dao.SearchItemDao;
import com.huang.demo1.dao.SearchItemMapper;
import com.huang.demo1.entity.SearchItem;
import com.huang.demo1.service.SearchItemService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    //查询数据库
    @Autowired
    private SearchItemMapper searchItemMapper;
    //添加索引库
    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SearchItemDao searchItemDao;

    @Override
    public Object dataImport() {
        //查询数据库
        List<SearchItem> searchList = searchItemMapper.dataImport();
        //循环数据集合，把每一个数据放入索引库
        for (SearchItem searchItem : searchList) {
            //创建文档对象，封装查询数据库数据集
            SolrInputDocument doc = new SolrInputDocument();
            //封装 Id
            doc.addField("id", searchItem.getId());
            //封装商品标题
            doc.addField("item_title", searchItem.getTitle());
            //封装商品买点
            doc.addField("item_sell_point", searchItem.getSell_point());
            //封装商品价格
            doc.addField("item_price", searchItem.getPrice());
            //封装商品图片地址
            doc.addField("item_image", searchItem.getImage());
            //商品类别
            doc.addField("item_category_name", searchItem.getCategory_name());
            //商品描述
            doc.addField("item_desc", searchItem.getItem_desc());
            try {
                solrClient.add(doc);
                //提交
                solrClient.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultUtils.getSuccess();
    }

    @Override
    public Object queryItem(String param, Integer page, Integer rows) {
        SolrQuery solrQuery = new SolrQuery();
        if (param != null && !"".equals(param)) {
            solrQuery.setQuery(param);
        }else {
            solrQuery.setQuery("*:*");
        }
        int startNO = (page - 1)* rows;
        solrQuery.setStart(startNO);
        solrQuery.setRows(rows);
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_titile");
        solrQuery.set("df","item_keywords");

        Result result = ResultUtils.getSuccess();
        List<SearchItem> searchItemList = new ArrayList<>();
        try{
            QueryResponse response = solrClient.query(solrQuery);
            SolrDocumentList solrResult = response.getResults();
            long numFound = solrResult.getNumFound();
            result.setData("recordCount",numFound);

            for (SolrDocument solrDocument : solrResult) {
                SearchItem item = new SearchItem();
                String id = (String)solrDocument.get("id");
                item.setId(Long.parseLong(id));
                String title = (String) solrDocument.get("item_title");
                Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
                Map<String, List<String>> maps = highlighting.get(id);
                List<String> list = maps.get("item_title");
                if (list!=null && list.size() >0) {
                    title = list.get(0);
                }
                item.setTitle(title);
                String sellPoint = (String) solrDocument.get("item_sell_point");
                item.setSell_point(sellPoint);
                Long price = (Long) solrDocument.get("item_price");
                item.setPrice(price);
                String categoryName = (String) solrDocument.get("item_category_name");
                item.setCategory_name(categoryName);
                String desc = (String) solrDocument.get("item_desc");
                item.setItem_desc(desc);
                searchItemList.add(item);
            }
            result.setData("items",searchItemList);
        } catch (Exception e){
            e.printStackTrace();
            return ResultUtils.getException();
        }
        return result;
    }


}
