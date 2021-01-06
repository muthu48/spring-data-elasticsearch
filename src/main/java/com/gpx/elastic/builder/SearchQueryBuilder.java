package com.gpx.elastic.builder;

import org.springframework.stereotype.Component;


@Component
public class SearchQueryBuilder {
	/*
	 * @Autowired private ElasticsearchTemplate elasticsearchTemplate;
	 * 
	 * public List<Users> getAll(String text) {
	 * 
	 * QueryBuilder query = QueryBuilders.boolQuery()
	 * .should(QueryBuilders.simpleQueryStringQuery(text) .lenient(true)
	 * .field("name")
	 * .field("teamName")).should(QueryBuilders.queryStringQuery("*"+text+"*"));
	 * 
	 * NativeSearchQuery build = new
	 * NativeSearchQueryBuilder().withQuery(query).build();
	 * 
	 * List<Users> userses = elasticsearchTemplate.queryForList(build, Users.class);
	 * 
	 * return userses;
	 * 
	 * }
	 */
}
