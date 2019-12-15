package app.shareparking.com.spapp.features.chooseOptionActivity;

public class ChooseOptionPresenter implements ChooseOptionContract.ChooseOptionContractPresenter {

    ChooseOptionContract.ChooseOptionContractView view;

    public ChooseOptionPresenter(ChooseOptionContract.ChooseOptionContractView view){
        this.view = view;
    }


}
