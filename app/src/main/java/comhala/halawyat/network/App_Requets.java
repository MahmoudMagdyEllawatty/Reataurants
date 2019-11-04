package comhala.halawyat.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;


import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import comhala.halawyat.helper.ConstantsOfApp;
import comhala.halawyat.network.model.AreaModel;
import comhala.halawyat.network.model.GetCartModel;
import comhala.halawyat.network.model.LgoinModel;
import comhala.halawyat.network.model.MyOrdersModel;
import comhala.halawyat.network.model.NotificationModel;
import comhala.halawyat.network.model.ProductModel;
import comhala.halawyat.network.model.SettingModel;
import comhala.halawyat.network.model.ShopModel;
import comhala.halawyat.network.model.Singupmodel;
import comhala.halawyat.network.model.StoreDetailModel;
import comhala.halawyat.network.model.addcartmodel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class App_Requets {
    Context ctx;
    Request_Complete request_complete;
    public static KProgressHUD mProgressDialog;

    public App_Requets(Context context) {
        this.ctx = context;
        mProgressDialog = ConstantsOfApp.showLoadingDialog(ctx);
        request_complete = (Request_Complete) ctx;


    }

    public void Do_Register( HashMap<String,String> hashMap) {
        new AppApiHelper().doRegisterApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Singupmodel.Get_Signup>() {
                    @Override
                    public void accept(Singupmodel.Get_Signup reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }

    public void Do_Verfiy_Code(HashMap<String,String> hashMap) {
        new AppApiHelper().doverify_codeApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LgoinModel.Get_Login>() {
                    @Override
                    public void accept(LgoinModel.Get_Login reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }

    public void Do_SendCode(HashMap<String, String> hashMap) {
        new AppApiHelper().doSendCodeApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer< Singupmodel.Get_Signup>() {
                    @Override
                    public void accept( Singupmodel.Get_Signup reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Login(HashMap<String, String> hashMap) {
        new AppApiHelper().doLoginApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LgoinModel.Get_Login>() {
                    @Override
                    public void accept( LgoinModel.Get_Login reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Areas(HashMap<String, String> hashMap) {
        new AppApiHelper().doAreasApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AreaModel.Get_Area>() {
                    @Override
                    public void accept(AreaModel.Get_Area reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Shops(HashMap<String, String> hashMap) {
        new AppApiHelper().doStoresApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopModel.Get_Shops>() {
                    @Override
                    public void accept(ShopModel.Get_Shops reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Shops_Details(HashMap<String, String> hashMap) {
        new AppApiHelper().doStore_DetailsApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<StoreDetailModel.Get_Store>() {
                    @Override
                    public void accept(StoreDetailModel.Get_Store reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Cate_Details(HashMap<String, String> hashMap) {
        new AppApiHelper().doCate_productsApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductModel>() {
                    @Override
                    public void accept(ProductModel reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_AddCart(HashMap<String, String> hashMap) {
        new AppApiHelper().doADDCartApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<addcartmodel>() {
                    @Override
                    public void accept(addcartmodel reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_RemoveCart(HashMap<String, String> hashMap) {
        new AppApiHelper().doremoveCartApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<addcartmodel>() {
                    @Override
                    public void accept(addcartmodel reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_GetCart(HashMap<String, String> hashMap) {
        new AppApiHelper().doGetCartApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GetCartModel.Get_Cart>() {
                    @Override
                    public void accept(GetCartModel.Get_Cart reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Order(HashMap<String, String> hashMap) {
        new AppApiHelper().doCreateOrderApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<addcartmodel>() {
                    @Override
                    public void accept(addcartmodel reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Get_MyOrders(HashMap<String, String> hashMap) {
        new AppApiHelper().doGetOrdersApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyOrdersModel.Get_MyOrder>() {
                    @Override
                    public void accept(MyOrdersModel.Get_MyOrder reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Contactus(HashMap<String, String> hashMap) {
        new AppApiHelper().docontactApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<addcartmodel>() {
                    @Override
                    public void accept(addcartmodel reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();
                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");

                    }
                });
    }
    public void Do_settings(HashMap<String, String> hashMap) {
        new AppApiHelper().dosettingsApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SettingModel.GetSetting>() {
                    @Override
                    public void accept(SettingModel.GetSetting reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Get_Notifation(HashMap<String, String> hashMap) {
        new AppApiHelper().doGetNotApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NotificationModel.Get_Notif>() {
                    @Override
                    public void accept(NotificationModel.Get_Notif reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();


                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");


                    }
                });
    }
    public void Do_Editprofile(HashMap<String,String>  hashMap) {
        new AppApiHelper().doEditProfileApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer< LgoinModel.Get_Login>() {
                    @Override
                    public void accept( LgoinModel.Get_Login reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                });
    }
    public void Do_Editpavatar(HashMap<String,String>  hashMap, File file) {
        new AppApiHelper().doEditavaterApiCall(hashMap,file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer< LgoinModel.Get_Login>() {
                    @Override
                    public void accept( LgoinModel.Get_Login reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                });
    }
    public void Do_Coangepassw(HashMap<String, String> hashMap) {
        new AppApiHelper().doChangePasswordApiCall(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<addcartmodel>() {
                    @Override
                    public void accept(addcartmodel reLoginAPi) throws Exception {
                        mProgressDialog.dismiss();
                        request_complete.Done_Request(reLoginAPi);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable e) throws Exception {
                        mProgressDialog.dismiss();
                        Log.e("Ddd", "errrrrrrrrrrrrrrrrrrrror");

                    }
                });
    }
    public interface Request_Complete {
        void Done_Request(Object object);
    }

}
