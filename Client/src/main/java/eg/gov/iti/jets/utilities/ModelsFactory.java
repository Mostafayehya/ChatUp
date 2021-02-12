package eg.gov.iti.jets.utilities;

import domains.User;
import eg.gov.iti.jets.ui.models.UserModel;

public class ModelsFactory {
    private static ModelsFactory modelsFactory;
    //has reference from all models
    UserModel currentUser;
    private ModelsFactory(){

    }
    public synchronized static ModelsFactory getInstance(){
        if(modelsFactory==null)
            modelsFactory=new ModelsFactory();
        return modelsFactory;
    }

    public void setCurrentUser(User user){
        currentUser = new UserModel(user.getPhoneNumber(),user.getName(),user.getEmail(),user.getPassword(),user.getPicture()
         ,user.getGender(),user.getCountry(),user.getDateOfBirth(),user.getBio(),user.getStatus(),user.getMode());
    }

    public UserModel getCurrentUser(){
        return currentUser;
    }


}
