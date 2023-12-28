package com.buyNowKart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.hibernate.annotations.GenericGenerator;

import java.io.IOException;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	@GenericGenerator(name = "native",strategy = "native")
	private long id;
	private String name;
	private String description;
	private String image;
	private int discount;
	private int quantity;
	private int price;
	@ManyToOne
	private Category category;

	public Product(String name, String description, String image, int discount, int quantity, int price,
			Category category) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
	}
	public Product(String name, int quantity, int price, Category category) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
	}
	public Product(String name, int quantity, int price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
	}

	public static class ProductDeserializer extends JsonDeserializer<Product> {
		@Override
		public Product deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			final Product instance = new Product();
			String field;
			while(p.nextToken() != JsonToken.END_OBJECT){
				if(p.getCurrentToken() == JsonToken.FIELD_NAME){
					field = p.getCurrentName();
					instance.populateFromJson(field,p);
				}
				else if(p.getCurrentToken() == JsonToken.START_OBJECT || p.getCurrentToken() == JsonToken.START_ARRAY){
					p.skipChildren();
				}
			}
			return instance;
		}
	}

	public void populateFromJson(String field, JsonParser p) throws IOException {
		switch (field) {
			case "id":
				p.nextToken();
				this.id = p.readValueAs(Long.class);
				break;
			case "name":
				p.nextToken();
				this.name = p.readValueAs(String.class);
				break;
			case "description":
				p.nextToken();
				this.description = p.readValueAs(String.class);
				break;
			case "image":
				p.nextToken();
				this.image = p.readValueAs(String.class);
				break;
			case "discount":
				p.nextToken();
				this.discount = p.readValueAs(Integer.class);
				break;
			case "quantity":
				p.nextToken();
				this.quantity = p.readValueAs(Integer.class);
				break;
			case "price":
				p.nextToken();
				this.price = p.readValueAs(Integer.class);
				break;
			case "category":
				p.nextToken();
				this.category = Category.getCategoryById(p.readValueAs(Long.class));
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", discount=" + discount + ", quantity=" + quantity + ", price=" + price + ", category=" + category
				+ "]";
	}

}
