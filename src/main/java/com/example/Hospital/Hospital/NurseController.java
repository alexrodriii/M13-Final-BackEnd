package com.example.Hospital.Hospital;
import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class NurseController {
	 
	   ArrayList<Nurse> nurses = new ArrayList<>();
	   public NurseController() {
		   
      nurses.add(new Nurse("Alex Rodriguez", 19, "alex19","Oftalmologia" ));
       nurses.add(new Nurse("Dafne Ramirez", 20,"dafne20","Psicologia" ));
        nurses.add(new Nurse("Noemi Saladie", 22,"noemi22","Cirugía"));
	   }


@GetMapping("/nurses")
	public ArrayList <Nurse> getAll(){
	
	   	    for(int i =0; i < nurses.size();i++) {
			    System.out.println(nurses.get(i));
				
	   	    
	 }
			return nurses;
			
}

	
	
}
