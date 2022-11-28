package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class VideoGameDemo 
{
	@Test(priority = 1)
	public void GetAllVideoGames()
	{
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200)
		;
	}
	@Test(priority = 2)
	public void PostVideoGame()
	{
		HashMap map = new HashMap();
		map.put("id", "25");
		map.put("name", "IronMan");
		map.put("releaseDate", "2022-11-28T12:19:09.792Z");
		map.put("reviewScore", "25");
		map.put("category", "Xyz");
		map.put("rating", "Top");
		
		Response response=
				
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.post("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String res= response.asString();
		
		Assert.assertEquals(res.contains("Record Added Successfully"),true );
	}
	@Test(priority = 3)
	public void PutVideoGame()
	{
		HashMap map = new HashMap();
		map.put("id", "25");
		map.put("name", "BatMan");
		map.put("releaseDate", "2022-11-28T12:19:09.792Z");
		map.put("reviewScore", "25");
		map.put("category", "Xyz");
		map.put("rating", "Top");
		
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.put("http://localhost:8080/app/videogames/3")
		.then()
			.statusCode(200)
			.body("videoGame.id",equalTo("3"))
			.log().body()
		;
	}
	@Test(priority = 4)
	public void getVideoGame()
	{
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames/3")
		.then()
			.statusCode(200)
			.body("videoGame.id",equalTo("3"))
			.log().body()
		;
	}
	@Test(priority = 5)
	public void deleteVideoGame()
	{
		Response res=
				
		given()
		
		.when()
			.delete("http://localhost:8080/app/videogames/3")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String response = res.asString();
		
		Assert.assertEquals(response.contains("Record Deleted Successfully"), true);
	}
}
