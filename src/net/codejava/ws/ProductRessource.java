package net.codejava.ws;

import java.awt.MediaTracker;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/product")
public class ProductRessource {
	private ProductDAO dao = ProductDAO.getinstance();
	
	@GET
	//@Path("/lsitproduit")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> listAll(){
		
		return dao.listAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product prd) throws URISyntaxException {
		
		int newid = dao.addProduct(prd);
		URI uri = new URI("/MyWebsite/rest/product/"+newid);
		
		return Response.created(uri).build();
	}
	
	@GET
	@Path("{id}")
	public Response getPro(@PathParam("id") int id) {
		
		
		Product prod=  dao.getProduct(id);
		if(prod !=null) {
			return Response.ok(prod, MediaType.APPLICATION_JSON).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
			
		
		
	}
	
	
	/*
	@GET
	@PathParam("{idprd}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("idprd") int idprd) {
		
		Product prd = dao.getProduct(idprd);
		if(prd != null) {
			return Response.ok(prd, MediaType.APPLICATION_JSON).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}*/
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Product prd) {
		boolean bol = dao.updateProduct(prd);
		if(bol) {
			return Response.ok().build();
		}else {
			return Response.notModified().build();
		}
		
	}
	
	@DELETE
	@Path("{idprd}")
		
	public Response delete(@PathParam("idprd") int idprd) {
		
		dao.delete(idprd);
		if(dao.delete(idprd)) {
			return Response.ok().build();
		}else
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
}
