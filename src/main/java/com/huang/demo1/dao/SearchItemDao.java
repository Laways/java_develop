package com.huang.demo1.dao;

import com.huang.demo1.common.utils.Result;
import org.apache.solr.client.solrj.SolrQuery;

public interface SearchItemDao {
    Result queryItem(SolrQuery solrQuery);
}
