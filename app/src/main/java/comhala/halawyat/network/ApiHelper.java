/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package comhala.halawyat.network;




import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

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


public interface ApiHelper {

    Single<Singupmodel.Get_Signup> doRegisterApiCall( HashMap<String,String> request);
    Single<LgoinModel.Get_Login> doverify_codeApiCall(HashMap<String,String> request);
    Single< Singupmodel.Get_Signup> doSendCodeApiCall(HashMap<String, String> request);
    Single<LgoinModel.Get_Login> doLoginApiCall(HashMap<String, String> request);
    Single<AreaModel.Get_Area> doAreasApiCall(HashMap<String, String> request);
    Single<ShopModel.Get_Shops> doStoresApiCall(HashMap<String, String> request);
    Single<StoreDetailModel.Get_Store> doStore_DetailsApiCall(HashMap<String, String> request);
    Single<ProductModel> doCate_productsApiCall(HashMap<String, String> request);
    Single<addcartmodel> doADDCartApiCall(HashMap<String, String> request);
    Single<addcartmodel> doremoveCartApiCall(HashMap<String, String> request);
    Single<GetCartModel.Get_Cart> doGetCartApiCall(HashMap<String, String> request);
    Single<addcartmodel> doCreateOrderApiCall(HashMap<String, String> request);
    Single<MyOrdersModel.Get_MyOrder> doGetOrdersApiCall(HashMap<String, String> request);
    Single<addcartmodel> docontactApiCall(HashMap<String, String> request);
    Single<SettingModel.GetSetting> dosettingsApiCall(HashMap<String, String> request);
    Single<NotificationModel.Get_Notif> doGetNotApiCall(HashMap<String, String> request);
    Single<LgoinModel.Get_Login> doEditProfileApiCall(HashMap<String, String> request);
    Single<LgoinModel.Get_Login> doEditavaterApiCall(HashMap<String, String> request, File file);
    Single<addcartmodel> doChangePasswordApiCall(HashMap<String, String> request);








}
