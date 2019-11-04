
package comhala.halawyat.network;


import com.rx2androidnetworking.Rx2AndroidNetworking;


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
import io.reactivex.Single;
import okhttp3.Credentials;


public class AppApiHelper implements ApiHelper {


    @Override
    public Single<Singupmodel.Get_Signup> doRegisterApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_register)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .setContentType("application/json; charset=UTF-8")
                .build()
                .getObjectSingle(Singupmodel.Get_Signup.class);
    }

    @Override
    public Single<LgoinModel.Get_Login> doverify_codeApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_verifycode)
                .addHeaders("Content-Type", "application/json")
                .setContentType("application/json; charset=UTF-8")
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LgoinModel.Get_Login.class);
    }

    @Override
    public Single<Singupmodel.Get_Signup> doSendCodeApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINTsend_code)
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(request)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .setContentType("application/json; charset=UTF-8")
                .build()
                .getObjectSingle(Singupmodel.Get_Signup.class);
    }

    @Override
    public Single<LgoinModel.Get_Login> doLoginApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_Login)
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(request)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .setContentType("application/json; charset=UTF-8")
                .build()
                .getObjectSingle(LgoinModel.Get_Login.class);
    }

    @Override
    public Single<AreaModel.Get_Area> doAreasApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_regions)
                .addHeaders("Content-Type", "application/json")
                .addQueryParameter(request)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .build()
                .getObjectSingle(AreaModel.Get_Area.class);
    }

    @Override
    public Single<ShopModel.Get_Shops> doStoresApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_home)
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(request)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .build()
                .getObjectSingle(ShopModel.Get_Shops.class);
    }

    @Override
    public Single<StoreDetailModel.Get_Store> doStore_DetailsApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_shop_details)
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(request)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .build()
                .getObjectSingle(StoreDetailModel.Get_Store.class);
    }

    @Override
    public Single<ProductModel> doCate_productsApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_category_products)
                .addHeaders("Content-Type", "application/json")
                .addBodyParameter(request)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .build()
                .getObjectSingle(ProductModel.class);
    }

    @Override
    public Single<addcartmodel> doADDCartApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_addtocart)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(addcartmodel.class);
    }

    @Override
    public Single<addcartmodel> doremoveCartApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_removecart)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(addcartmodel.class);
    }

    @Override
    public Single<GetCartModel.Get_Cart> doGetCartApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_Getcart)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(GetCartModel.Get_Cart.class);
    }

    @Override
    public Single<addcartmodel> doCreateOrderApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_create_order)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(addcartmodel.class);
    }

    @Override
    public Single<MyOrdersModel.Get_MyOrder> doGetOrdersApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_orders)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(MyOrdersModel.Get_MyOrder.class);
    }

    @Override
    public Single<addcartmodel> docontactApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_contact)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(addcartmodel.class);
    }

    @Override
    public Single<SettingModel.GetSetting> dosettingsApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_settings)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addQueryParameter(request)
                .build()
                .getObjectSingle(SettingModel.GetSetting.class);
    }

    @Override
    public Single<NotificationModel.Get_Notif> doGetNotApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_notifications)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("jwt", ConstantsOfApp.GetAccessToken())
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addQueryParameter(request)
                .build()
                .getObjectSingle(NotificationModel.Get_Notif.class);
    }

    @Override
    public Single<LgoinModel.Get_Login> doEditProfileApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EditProfile)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addHeaders("Content-Type","application/json")
                .addHeaders("jwt",ConstantsOfApp.GetAccessToken())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LgoinModel.Get_Login.class);
    }

    @Override
    public Single<LgoinModel.Get_Login> doEditavaterApiCall(HashMap<String, String> request, File file) {
        return Rx2AndroidNetworking.upload(ApiEndPoint.ENDPOINT_Editavater)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addHeaders("Content-Type","application/json")
                .addHeaders("jwt",ConstantsOfApp.GetAccessToken())
                .addMultipartFile("image",file)
                .addMultipartParameter(request)
                .build()
                .getObjectSingle(LgoinModel.Get_Login.class);
    }

    @Override
    public Single<addcartmodel> doChangePasswordApiCall(HashMap<String, String> request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_changepassword)
                .addHeaders("lang", ConstantsOfApp.GetDEFAULT_LANGUAGE())
                .addHeaders("Content-Type","application/json")
                .addHeaders("jwt",ConstantsOfApp.GetAccessToken())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(addcartmodel.class);
    }


}

