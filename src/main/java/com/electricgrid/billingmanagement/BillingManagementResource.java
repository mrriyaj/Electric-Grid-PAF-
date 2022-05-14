package com.electricgrid.billingmanagement;

import java.util.List;

import com.google.gson.Gson;




import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/billings")

public class BillingManagementResource {
	
	BillingmanagementRepository repo = new BillingmanagementRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Billingmanagement> getBillingmanagements() {
		return repo.getBillingmanagements();
    }
	
    @GET
    @Path("billing/{billing_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Billingmanagement getBillingmanagement(@PathParam("billing_id") int billing_id) {

        return repo.getBillingmanagement(billing_id);

    }
    
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createbillingmanagement(Billingmanagement b1) {
		return repo.createbillingmanagement(b1);
	}
	
	@DELETE
	@Path("/delete/{billing_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteBillingmanagement(@PathParam("billing_id") int billing_id) {
		return repo.deleteBillingmanagement(billing_id);
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateBillingmanagement(Billingmanagement b1) 
	{ 
		return repo.updateBillingmanagement(b1);
	}
	
}