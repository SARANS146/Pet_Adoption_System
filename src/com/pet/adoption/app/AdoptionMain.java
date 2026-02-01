package com.pet.adoption.app;

import java.sql.Date;
import java.util.Scanner;

import com.pet.adoption.service.AdoptionService;
import com.pet.adoption.util.PetNotAvailableException;
import com.pet.adoption.util.ValidationException;

public class AdoptionMain {

    private static AdoptionService adoptionService;

    public static void main(String[] args) {

        adoptionService = new AdoptionService();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Pet Adoption Console ---");

        try {
            boolean r = adoptionService.adoptPet(
                "PET1002",
                "Karan Rao",
                "karan.r@example.com",
                new Date(System.currentTimeMillis())
            );
            System.out.println(r ? "ADOPTED" : "FAILED");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            boolean r = adoptionService.adoptPet(
            	"PET1003",
            		"Anita Bose",
            		"anita.b@example.com"           ,
                new Date(System.currentTimeMillis())
            );
            System.out.println(r ? "ADOPTED" : "FAILED");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            boolean r = adoptionService.adoptPet("PET1003",
            		"Sonia Mehta",
            		"sonia.m@example.com",
            		new Date(System.currentTimeMillis()));
            System.out.println(r ? "ADOPTED" : "FAILED DUE TO MEDICAL STATUS");
        }
            catch(Exception e) {
            e.printStackTrace();
        }
        
        try {
        	boolean r = adoptionService.adoptPet("PET1004", "Arjun Singh", "arjun@mail.com", 
        		new java.sql.Date(System.currentTimeMillis()));
            System.out.println(r ? "ADOPTED" : "FAILED DUE TO MEDICAL STATUS");
        }
            catch(Exception e) {
            e.printStackTrace();
        }

        try {
            boolean r = adoptionService.cancelAdoption(160001);
            System.out.println(r ? "CANCELLED" : "FAILED");
        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();
    }
}
