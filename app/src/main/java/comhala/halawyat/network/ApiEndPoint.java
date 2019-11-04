
package comhala.halawyat.network;


import comhala.halawyat.BuildConfig;

public final class ApiEndPoint {


    public static final String ENDPOINT_register= BuildConfig.BASE_URL + "user/signup";
    public static final String ENDPOINT_verifycode= BuildConfig.BASE_URL + "user/verify-code";
    public static final String ENDPOINTsend_code= BuildConfig.BASE_URL + "user/send-code";
    public static final String ENDPOINT_Login= BuildConfig.BASE_URL + "user/login";
    public static final String ENDPOINT_regions= BuildConfig.BASE_URL + "user/regions";
    public static final String ENDPOINT_home= BuildConfig.BASE_URL + "user/home";
    public static final String ENDPOINT_shop_details= BuildConfig.BASE_URL + "user/shop-details";
    public static final String ENDPOINT_category_products= BuildConfig.BASE_URL + "user/category-products";
    public static final String ENDPOINT_addtocart= BuildConfig.BASE_URL + "user/add-to-cart";
    public static final String ENDPOINT_removecart= BuildConfig.BASE_URL + "user/remove-from-cart";
    public static final String ENDPOINT_Getcart= BuildConfig.BASE_URL + "user/cart";
    public static final String ENDPOINT_create_order= BuildConfig.BASE_URL + "user/create-order";
    public static final String ENDPOINT_orders= BuildConfig.BASE_URL + "user/orders";
    public static final String ENDPOINT_contact= BuildConfig.BASE_URL + "user/contact";
    public static final String ENDPOINT_settings= BuildConfig.BASE_URL + "user/settings";
    public static final String ENDPOINT_notifications= BuildConfig.BASE_URL + "user/notifications";
    public static final String ENDPOINT_EditProfile= BuildConfig.BASE_URL + "user/update-profile";
    public static final String ENDPOINT_Editavater= BuildConfig.BASE_URL + "user/update-profile";
    public static final String ENDPOINT_changepassword= BuildConfig.BASE_URL + "user/change-password";




    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

}
