package com.huang.demo1.dao.impl;

import com.huang.demo1.common.ResultUtils;
import com.huang.demo1.common.utils.Result;
import com.huang.demo1.dao.SearchItemDao;
import com.huang.demo1.entity.SearchItem;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SearchItemDaoImpl implements SearchItemDao {

    @Autowired
    private SolrClient solrClient;

    @Override
    public Result queryItem(SolrQuery solrQuery) {
/*        Result result = ResultUtils.getSuccess();
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
        } catch (Exception e){
            e.printStackTrace();
            return ResultUtils.getException();
        }
        return result;*/
        return null;
    }
}
