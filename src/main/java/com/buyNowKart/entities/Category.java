package com.buyNowKart.entities;

import java.io.IOException;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.buyNowKart.dao.DaoManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private long id;
	@Column(name="category_name")
	private String name;
	@Column(name = "category_description")
	private String description;
	@OneToMany(mappedBy = "category")
	private List<Product> pdtsList;

	public static transient volatile Map<Long,Category> categoryIdMap;
	public Category(String name, String description, List<Product> pdtsList) {
		super();
		this.name = name;
		this.description = description;
		this.pdtsList = pdtsList;
	}
	public Category( String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Category() {
	}

	public static void initialize(){
		categoryIdMap = new HashMap<>();
		List<Category> allCategories = DaoManager.categoryDao().getAllCategories();

		for(Category category : allCategories){
			categoryIdMap.put(category.getId(),category);
		}
	}


	public static class CategoryDeserializer extends JsonDeserializer<Category> {
		@Override
		public Category deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException {
			final Category instance = new Category();
			String field;
			while (p.nextToken() != JsonToken.END_OBJECT) {
				if (p.getCurrentToken() == JsonToken.FIELD_NAME) {
					field = p.getCurrentName();
					instance.populateFromJsonString(field, p);
				} else if (p.getCurrentToken() == JsonToken.START_OBJECT
						|| p.getCurrentToken() == JsonToken.START_ARRAY) {
					p.skipChildren();
				}
			}
			return instance;
		}
	}

	public void populateFromJsonString(String field, JsonParser p) throws IOException {
		switch (field) {
			case "id":
				p.nextToken();
				id = p.readValueAs(long.class);
				break;
			case "name":
				p.nextToken();
				name = p.readValueAs(String.class);
				break;
			case "description":
				p.nextToken();
				description = p.readValueAs(String.class);
				break;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Product> getPdtsList() {
		return pdtsList;
	}

	public void setPdtsList(List<Product> pdtsList) {
		this.pdtsList = pdtsList;
	}

	public static Map<Long,Category> getCategoryIdMap(){
		return Collections.unmodifiableMap(categoryIdMap);
	}

	public static Category getCategoryById(long id){
		return getCategoryIdMap().get(id);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", pdtsList=" + pdtsList
				+ "]";
	}

}
