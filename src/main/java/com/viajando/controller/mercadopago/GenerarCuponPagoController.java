package com.viajando.controller.mercadopago;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.viajando.domain.Producto;

@WebServlet(urlPatterns = "/generarCupon")
public class GenerarCuponPagoController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Properties properties = getProperties();
		String token = properties.getProperty("mercadopago.private.accesstoken");
		try {
		
			MercadoPago.SDK.setAccessToken(token);
			String notificationUrl = "http://localhost:8080/generic";

	        Preference p = new Preference();
	        p.setBackUrls(
	          new BackUrls().setSuccess(notificationUrl)
	                  .setPending(notificationUrl)
	                .setFailure(notificationUrl)
	        );
	        List<Producto> productos = new ArrayList<Producto>();
	        productos.add(new Producto(1, 10000, "es una descripcion", "producto1"));
	        productos.add(new Producto(2, 30000, "es una descripcion", "producto2"));
	        
	        p.setItems(productos.stream()
	                .map(i -> {
	                    Item item = new Item();
	                    //casteo
	                    float precio = (float)i.getPrecio();
	                    
	                    item.setUnitPrice(precio);
	                    item.setTitle(i.getDescripcion());
	                    item.setQuantity(1);
	                    return item;
	                })
	                .collect(Collectors.toCollection(ArrayList::new)));

				p.save();
				System.out.println(p.getInitPoint());
				
				//url a donde deberia redirigir para que page
				System.out.println(p.getSandboxInitPoint());

		} catch (MPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private Properties getProperties() throws IOException {
		
	    InputStream inputStream;
	    
		 Properties prop = new Properties();
         String propFileName = "environment.properties";
         inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
         if (inputStream != null) {
             prop.load(inputStream);
         } else {
             throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
         }
		
         inputStream.close();
         
         return prop;
	}
	
	
	
}
