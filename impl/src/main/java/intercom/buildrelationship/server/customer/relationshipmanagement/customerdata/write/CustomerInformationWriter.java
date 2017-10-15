package intercom.buildrelationship.server.customer.relationshipmanagement.customerdata.write;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import intercom.buildrelationship.object.criteria.offices.CustomerInformation;
import intercom.buildrelationship.object.response.Response;

/**
 * Writes customer information to the customer.text file.
 * @author Ritesh
 */
public class CustomerInformationWriter {

	public Response writeCustomerData(final CustomerInformation customerInformation) {
		
		final CustomerResponseImpl customerResponseImpl = new CustomerResponseImpl();
		customerResponseImpl.setName(customerInformation.getName());
		customerResponseImpl.setUserId(customerInformation.getUserId());
		
		final FailedResponseImpl failedResponseImpl = new FailedResponseImpl();
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
	    final List<String> customerInformations = new ArrayList<>();

		try {
			fileReader = new FileReader("/Users/ritesh/Documents/Ritesh/intercom/impl/src/main/resources/customer.text");
			bufferedReader = new BufferedReader(fileReader);
			
		    String content = null;
		   
		    while((content=bufferedReader.readLine())!=null) {
		    		customerInformations.add(content);
		    }
		}catch(final FileNotFoundException fileNotFoundException) {
			failedResponseImpl.setException(fileNotFoundException);
			failedResponseImpl.setReasonForFailure("File not found");
			
		}catch (IOException ioException) {
			failedResponseImpl.setException(ioException);
			failedResponseImpl.setReasonForFailure("Error while reading the data");
			
		}finally {
			
			if(fileReader!=null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(bufferedReader!=null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		final Gson gson = new Gson();
		final String contentToWrite = gson.toJson(customerInformation);
		
		customerInformations.add(contentToWrite);
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter("/Users/ritesh/Documents/Ritesh/intercom/impl/src/main/resources/customer.text");
			bufferedWriter = new BufferedWriter(fileWriter);
			
			for(String cust : customerInformations) {
				bufferedWriter.write(cust);
			}
		}catch(final FileNotFoundException fileNotFoundException) {
			failedResponseImpl.setException(fileNotFoundException);
			failedResponseImpl.setReasonForFailure("File not found");
		}catch (IOException ioException) {
			failedResponseImpl.setException(ioException);
			failedResponseImpl.setReasonForFailure("Error while writing the data");
		}finally {
			
			if(fileWriter!=null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(bufferedWriter!=null) {
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		final ResponseImpl responseImpl = new ResponseImpl();
		responseImpl.setCustomerResponse(customerResponseImpl);
		responseImpl.setFailedResponse(failedResponseImpl);
		
		return responseImpl;
	}
}
