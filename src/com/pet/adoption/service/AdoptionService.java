package com.pet.adoption.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.pet.adoption.bean.Adoption;
import com.pet.adoption.bean.Pet;
import com.pet.adoption.dao.AdoptionDAO;
import com.pet.adoption.dao.PetDAO;
import com.pet.adoption.util.ActiveAdoptionException;
import com.pet.adoption.util.DBUtil;
import com.pet.adoption.util.PetNotAvailableException;
import com.pet.adoption.util.ValidationException;

public class AdoptionService {

    private PetDAO petDAO;
    private AdoptionDAO adoptionDAO;

    public AdoptionService() {
        petDAO = new PetDAO();
        adoptionDAO = new AdoptionDAO();
    }

    public Pet viewPetDetails(String petID) throws ValidationException {
        if (petID == null || petID.trim().isEmpty()) {
            throw new ValidationException();
        }
        return petDAO.findPet(petID);
    }

    public List<Pet> viewAllPets() {
        return petDAO.viewAllPets();
    }

    public boolean addNewPet(Pet pet) throws ValidationException {
        if (pet == null ||
            pet.getPetID() == null || pet.getPetID().trim().isEmpty() ||
            pet.getName() == null || pet.getName().trim().isEmpty() ||
            pet.getSpecies() == null || pet.getSpecies().trim().isEmpty() ||
            pet.getAge() < 0) {
            throw new ValidationException();
        }

        if (petDAO.findPet(pet.getPetID()) != null) {
            return false;
        }

        return petDAO.insertPet(pet);
    }

    public boolean removePet(String petID)
            throws ValidationException, ActiveAdoptionException {

        if (petID == null || petID.trim().isEmpty()) {
            throw new ValidationException();
        }

        if (adoptionDAO.hasActiveAdoptions(petID)) {
            throw new ActiveAdoptionException();
        }

        return petDAO.deletePet(petID);
    }

    public boolean adoptPet(String petID,
                            String adopterName,
                            String adopterContact,
                            java.util.Date adoptionDate)
            throws ValidationException, PetNotAvailableException {

        if (petID == null || petID.trim().isEmpty() ||
            adopterName == null || adopterName.trim().isEmpty() ||
            adopterContact == null || adopterContact.trim().isEmpty() ||
            adoptionDate == null) {
            throw new ValidationException();
        }

        Pet pet = petDAO.findPet(petID);
        if (pet == null) {
            return false;
        }

        if ("NO".equalsIgnoreCase(pet.getAvailable())) {
            throw new PetNotAvailableException();
        }

        if ("NeedsTreatment".equalsIgnoreCase(pet.getMedicalStatus()) ||
            "UnderObservation".equalsIgnoreCase(pet.getMedicalStatus())) {
            return false;
        }

        Connection con = null;
        try {
            con = DBUtil.getDBConnection();
            con.setAutoCommit(false);

            int adoptionID = adoptionDAO.generateAdoptionID();

            Adoption adoption = new Adoption(adoptionID, adopterContact, adopterContact, adopterContact, null, adopterContact, adoptionID);
            adoption.setAdoptionlD(adoptionID);
            adoption.setPetID(petID);
            adoption.setAdopterName(adopterName);
            adoption.setAdopterContact(adopterContact);
            adoption.setAdoptionDate(new Date(adoptionDate.getTime()));
            adoption.setStatus("ADOPTED");

            boolean a1 = adoptionDAO.recordAdoption(adoption);
            boolean a2 = petDAO.updateAvailability(petID, "NO");

            if (a1 && a2) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) { }
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) { }
        }
    }

    public boolean cancelAdoption(int adoptionID) throws ValidationException {

        if (adoptionID <= 0) {
            throw new ValidationException();
        }

        Connection con = null;
        try {
            con = DBUtil.getDBConnection();
            con.setAutoCommit(false);

            boolean result = adoptionDAO.cancelAdoption(adoptionID);

            if (result) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }

        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (Exception ex) { }
            return false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) { }
        }
    }

}
