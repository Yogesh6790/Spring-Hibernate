package com.test.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.test.pojo.Base;
import com.test.pojo.Base2;
import com.test.pojo.List2;
import com.test.pojo.Weather;
import com.test.util.HibernateUtil;


@Repository
public class TestDao implements ITestDao {
	@Override
	public List getDetails(String country) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet("http://samples.openweathermap.org/data/2.5/history/city?q=" + country
				+ "&APPID=aba2282146378a290ed9d2079cf2ca53");
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output = br.readLine();
		System.out.println(output);
		httpClient.getConnectionManager().shutdown();
		System.out.println("Output from Server .... \n");
		if (output != null) {
			Gson gsonObj = new Gson();
			Base obj = (Base) gsonObj.fromJson(output, Base.class);
			obj.setName(country);
			persistData(obj);
		}
		return getDetailsfromDB(country);
	}

	@Transactional
	private void persistData(Base obj) {
		Base2 base2Obj = new Base2();
		base2Obj.setCityId(obj.getCity_id());
		base2Obj.setName(obj.getName());
		
		java.util.List<com.test.pojo.List2> list2 = new ArrayList<>();
		java.util.List<com.test.pojo.List> list = obj.getList();
		for(com.test.pojo.List listObj : list){
			List2 list2Obj = new List2();
			list2Obj.setDt(listObj.getDt());
			list2Obj.setMain_humidity(listObj.getMain().getHumidity());
			list2Obj.setMain_pressure(listObj.getMain().getPressure());
			list2Obj.setMain_temp(listObj.getMain().getTemp());
			list2Obj.setWind_speed(listObj.getWind().getSpeed());
			String weatherStr  = "";
			for (Weather weather : listObj.getWeather()) {
				weatherStr += weather.getMain()+",";
			}
			weatherStr = weatherStr.substring(0,weatherStr.length()-1);
			list2Obj.setWeather(weatherStr);
			list2.add(list2Obj);
		}
		base2Obj.setList(list2);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(base2Obj);
		session.getTransaction().commit();
		session.close();
		System.out.println("Data Persisted to DB");
	}
	
	@Transactional
	private List getDetailsfromDB(String country) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		// forming-query
		CriteriaQuery<List2> cq = session.getCriteriaBuilder().createQuery(List2.class);
		Root<List2> c = cq.from(List2.class);
		cq.select(c);
		cq.orderBy(session.getCriteriaBuilder().desc(c.get("dt")));

		java.util.List<List2> detailsList = (java.util.List<List2>) session.createQuery(cq).getResultList();
		List<Map<String, String>> listOfMap = new ArrayList<>();
		for (List2 list2Obj : detailsList) {
			Map<String, String> map = new HashMap();
			map.put("country", country);
			
			//Unix timestamp to Normal timestamp
			Date date = new Date(list2Obj.getDt()*1000L); // *1000 is to convert seconds to milliseconds
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd,yyyy"); // the format of your date
			sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
			String formattedDate = sdf.format(date);
			map.put("date", formattedDate);
			
			map.put("main_humidity", list2Obj.getMain_humidity().toString());
			map.put("main_pressure", list2Obj.getMain_pressure().toString());
			
			//kelvin to celcius
			double temp = Math.round((list2Obj.getMain_temp() - 273.15) * 100.0) / 100.0;
			map.put("main_temp", String.valueOf(temp)); 
			map.put("wind_speed", list2Obj.getWind_speed().toString());
			map.put("weather_main", list2Obj.getWeather());
			listOfMap.add(map);
		}
		session.getTransaction().commit();
		session.close();
		System.out.println(listOfMap);
		return listOfMap;
	}

}
