// com.example.database.DatabaseHelper.java (cập nhật đầy đủ)
package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.models.Account;
import com.example.models.AddressInfo;
import com.example.models.Brand;
import com.example.models.Cart;
import com.example.models.CartItem;
import com.example.models.Category;
import com.example.models.Color;
import com.example.models.Customer;
import com.example.models.LineItem;
import com.example.models.Order;
import com.example.models.PaymentMethod;
import com.example.models.Product;
import com.example.models.ProductVariant;
import com.example.models.Promotion;
import com.example.models.PromotionType;
import com.example.models.Review;
import com.example.models.Room;
import com.example.models.ShipmentMethod;
import com.example.models.Size;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cabana_db.db";
    private static final int DATABASE_VERSION = 1;
    private static String DATABASE_PATH = "";
    private final Context myContext;

    private static final String TABLE_CUSTOMER = "customer";
    private static final String COL_CUSTOMER_ID = "customer_id";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_LAST_NAME = "last_name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE_NUMBER = "phone_number";
    private static final String COL_CREDIT = "credit";
    private static final String COL_AVATAR = "avatar";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        DATABASE_PATH = context.getFilesDir().getParentFile().getPath() + "/databases/";
        Log.d("DatabaseHelper", "Database path: " + DATABASE_PATH + DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DatabaseHelper", "Upgrading database from version " + oldVersion + " to " + newVersion);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase().close();
            copyDataBase();
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        String myPath = DATABASE_PATH + DATABASE_NAME;
        return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMER, null);
        if (cursor.moveToFirst()) {
            do {
                customerList.add(new Customer(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_CUSTOMER_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_LAST_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE_NUMBER)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_CREDIT)),
                        cursor.getBlob(cursor.getColumnIndexOrThrow(COL_AVATAR))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return customerList;
    }

    public List<Brand> getAllBrands() {
        List<Brand> brandList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM brand", null);
        if (cursor.moveToFirst()) {
            do {
                brandList.add(new Brand(
                        cursor.getInt(cursor.getColumnIndexOrThrow("brand_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("brand_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return brandList;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM category", null);
        if (cursor.moveToFirst()) {
            do {
                categories.add(new Category(
                        cursor.getInt(cursor.getColumnIndexOrThrow("category_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("category_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM order_table", null);
        if (cursor.moveToFirst()) {
            do {
                orders.add(new Order(
                        cursor.getInt(cursor.getColumnIndexOrThrow("order_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("status")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("customer_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("order_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("delivery_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("payment_date")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("payment_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("total_price")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("promotion_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("VAT")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("address_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("shipment_id"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orders;
    }

    public List<LineItem> getAllLineItems() {
        List<LineItem> lineItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM line_item", null);
        if (cursor.moveToFirst()) {
            do {
                lineItems.add(new LineItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("order_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("variant_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("discount_amount"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lineItems;
    }

    public List<AddressInfo> getAllAddressInfo() {
        List<AddressInfo> addresses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM address_info", null);
        if (cursor.moveToFirst()) {
            do {
                addresses.add(new AddressInfo(
                        cursor.getInt(cursor.getColumnIndexOrThrow("address_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("customer_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("is_default")),
                        cursor.getString(cursor.getColumnIndexOrThrow("delivery_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("contact_number")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getString(cursor.getColumnIndexOrThrow("address")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("delivery_fee"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return addresses;
    }

    public List<ShipmentMethod> getAllShipmentMethods() {
        List<ShipmentMethod> methods = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM shipment_methods", null);
        if (cursor.moveToFirst()) {
            do {
                methods.add(new ShipmentMethod(
                        cursor.getInt(cursor.getColumnIndexOrThrow("shipment_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("shipment_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getString(cursor.getColumnIndexOrThrow("contact_number")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("delivery_fee")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("delivery_status")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("delivery_time"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return methods;
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        List<PaymentMethod> methods = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM payment_method", null);
        if (cursor.moveToFirst()) {
            do {
                methods.add(new PaymentMethod(
                        cursor.getInt(cursor.getColumnIndexOrThrow("payment_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("payment_type")),
                        cursor.getString(cursor.getColumnIndexOrThrow("payment_name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getString(cursor.getColumnIndexOrThrow("payment_status")),
                        cursor.getString(cursor.getColumnIndexOrThrow("currency")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("transaction_fee"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return methods;
    }
    public List<Review> getAllReviews() {
        List<Review> reviewList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM review", null);

        if (cursor.moveToFirst()) {
            do {
                Review review = new Review();
                review.setReviewId(cursor.getInt(cursor.getColumnIndexOrThrow("review_id")));
                review.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow("customer_id")));
                review.setProductId(cursor.getInt(cursor.getColumnIndexOrThrow("product_id")));
                review.setRating(cursor.getInt(cursor.getColumnIndexOrThrow("rating")));
                review.setComment(cursor.getString(cursor.getColumnIndexOrThrow("comment")));
                review.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));
                reviewList.add(review);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close(); // Tùy theo cách bạn quản lý DB, nên đóng sau mỗi truy vấn nếu không dùng lâu dài
        return reviewList;

    }
    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(
                    "SELECT * FROM cart_item WHERE cart_id = ?",
                    new String[]{String.valueOf(cartId)}
            );

            if (cursor.moveToFirst()) {
                do {
                    items.add(new CartItem(
                            cursor.getInt(cursor.getColumnIndexOrThrow("cart_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("variant_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("quantity")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                    ));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error getting cart items: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return items;
    }
    public Product getProductById(int productId) {
        Product product = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM product WHERE product_id = ?", new String[]{String.valueOf(productId)});
            if (cursor.moveToFirst()) {
                product = new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("product_status")),
                        cursor.getString(cursor.getColumnIndexOrThrow("product_name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("brand_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("category_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("room_id")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getBlob(cursor.getColumnIndexOrThrow("image"))
                );
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error getting product by ID: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return product;
    }
    public List<Cart> getAllCarts() {
        List<Cart> cartList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM cart", null);
            if (cursor.moveToFirst()) {
                do {
                    cartList.add(new Cart(
                            cursor.getInt(cursor.getColumnIndexOrThrow("cart_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("account_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("created_at")),
                            cursor.getString(cursor.getColumnIndexOrThrow("updated_at"))
                    ));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error getting all carts: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return cartList;
    }
    public List<Color> getAllColors() {
        List<Color> colorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM color", null);
            if (cursor.moveToFirst()) {
                do {
                    int colorCode = cursor.getInt(cursor.getColumnIndexOrThrow("color_code"));
                    String colorName = cursor.getString(cursor.getColumnIndexOrThrow("color_name"));
                    byte[] imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));

                    Color color = new Color(colorCode, colorName, imageBlob);
                    colorList.add(color);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error getting all colors: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return colorList;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM product", null); // Đảm bảo tên bảng đúng
            if (cursor.moveToFirst()) {
                do {
                    int productId = cursor.getInt(cursor.getColumnIndexOrThrow("product_id"));
                    String productStatus = cursor.getString(cursor.getColumnIndexOrThrow("product_status"));
                    String productName = cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
                    int brandId = cursor.getInt(cursor.getColumnIndexOrThrow("brand_id"));
                    int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow("category_id"));
                    int roomId = cursor.getInt(cursor.getColumnIndexOrThrow("room_id"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                    byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));

                    Product product = new Product(productId, productStatus, productName,
                            brandId, categoryId, roomId, price, description, image);
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error getting all products: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return productList;
    }
    public List<ProductVariant> getProductVariantsByProductId(int productId) {
        List<ProductVariant> variantList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM product_variant WHERE product_id = ?", new String[]{String.valueOf(productId)});
            if (cursor.moveToFirst()) {
                do {
                    byte[] imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                    ProductVariant variant = new ProductVariant(
                            cursor.getInt(cursor.getColumnIndexOrThrow("variant_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("color_code")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("size_code")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("stock_quantity")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                            imageBlob
                    );
                    variantList.add(variant);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DBHelper", "getProductVariantsByProductId error: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return variantList;
    }
    public Color getColorByCode(int colorCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Color color = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(
                    "SELECT * FROM color WHERE color_code = ?",
                    new String[]{String.valueOf(colorCode)}
            );

            if (cursor.moveToFirst()) {
                byte[] imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("image")); // giả sử cột lưu ảnh tên là 'image'
                color = new Color(
                        cursor.getInt(cursor.getColumnIndexOrThrow("color_code")),
                        cursor.getString(cursor.getColumnIndexOrThrow("color_name")),
                        imageBlob
                );
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error in getColorByCode: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return color;
    }
    public Size getSizeByCode(int sizeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Size size = null;
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(
                    "SELECT * FROM size WHERE size_code = ?",
                    new String[]{String.valueOf(sizeCode)}
            );

            if (cursor.moveToFirst()) {
                size = new Size(
                        cursor.getInt(cursor.getColumnIndexOrThrow("size_code")),
                        cursor.getString(cursor.getColumnIndexOrThrow("size_name"))
                );
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error in getSizeByCode: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return size;
    }
    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM promotions", null);

            if (cursor.moveToFirst()) {
                do {
                    Promotion promotion = new Promotion(
                            cursor.getInt(cursor.getColumnIndexOrThrow("promotion_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("promotion_name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("description")),
                            cursor.getString(cursor.getColumnIndexOrThrow("start_date")),
                            cursor.getString(cursor.getColumnIndexOrThrow("end_date")),
                            cursor.getString(cursor.getColumnIndexOrThrow("discount_type")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("discount_value")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("max_discount")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("min_order_value")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("promotion_type_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("is_active"))
                    );
                    promotions.add(promotion);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error in getAllPromotions: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return promotions;
    }

    public List<PromotionType> getAllPromotionTypes() {
        List<PromotionType> promotionTypes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM promotion_type", null);

            if (cursor.moveToFirst()) {
                do {
                    PromotionType promotionType = new PromotionType(
                            cursor.getInt(cursor.getColumnIndexOrThrow("promotion_type_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("type_name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("description")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("is_active"))
                    );
                    promotionTypes.add(promotionType);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error in getAllPromotionTypes: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return promotionTypes;
    }
    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM room", null);
            if (cursor.moveToFirst()) {
                do {
                    Room room = new Room(
                            cursor.getInt(cursor.getColumnIndexOrThrow("room_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("room_name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("description"))
                    );
                    roomList.add(room);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error in getAllRooms: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return roomList;
    }
    public List<Size> getAllSizes() {
        List<Size> sizeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM size", null);
            if (cursor.moveToFirst()) {
                do {
                    Size size = new Size(
                            cursor.getInt(cursor.getColumnIndexOrThrow("size_code")),
                            cursor.getString(cursor.getColumnIndexOrThrow("size_name"))
                    );
                    sizeList.add(size);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error in getAllSizes: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return sizeList;
    }
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery("SELECT * FROM account", null); // Đảm bảo table tên là `account`
            if (cursor.moveToFirst()) {
                do {
                    Account acc = new Account(
                            cursor.getInt(cursor.getColumnIndexOrThrow("account_id")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("is_anonymous")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("customer_id")),
                            cursor.getString(cursor.getColumnIndexOrThrow("username")),
                            cursor.getString(cursor.getColumnIndexOrThrow("password")),
                            cursor.getInt(cursor.getColumnIndexOrThrow("points")),
                            cursor.getString(cursor.getColumnIndexOrThrow("status")),
                            cursor.getString(cursor.getColumnIndexOrThrow("rank"))
                    );
                    accountList.add(acc);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Lỗi truy vấn account: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return accountList;
    }
    public long insertAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        values.put("customer_id", account.getCustomerId());
        values.put("is_anonymous", account.getIsAnonymous());

        long id = -1;
        try {
            id = db.insert("account", null, values);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting account: " + e.getMessage());
        } finally {
            db.close();
        }

        return id;
    }
//    public long insertCustomer(Customer customer) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(COL_FIRST_NAME, customer.getFirstName());
//        values.put(COL_LAST_NAME, customer.getLastName());
//        values.put(COL_EMAIL, customer.getEmail());
//        values.put(COL_PHONE_NUMBER, customer.getPhoneNumber());
//        values.put(COL_CREDIT, customer.getCredit());
//        values.put(COL_AVATAR, customer.getAvatar()); // avatar dạng byte[]
//
//        long result = -1;
//        try {
//            result = db.insert(TABLE_CUSTOMER, null, values);
//        } catch (Exception e) {
//            Log.e("DatabaseHelper", "Error inserting customer: " + e.getMessage());
//        } finally {
//            db.close();
//        }
//
//        return result;
//    }
public long insertCustomer(Customer customer) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    values.put(COL_FIRST_NAME, customer.getFirstName());
    values.put(COL_LAST_NAME, customer.getLastName());
    values.put(COL_EMAIL, customer.getEmail());
    values.put(COL_PHONE_NUMBER, customer.getPhoneNumber());

    // ✅ Gán mặc định nếu chưa gọi setCredit
    values.put(COL_CREDIT, customer.getCredit() == 0 ? 0 : customer.getCredit());

    // ✅ Avatar có thể null, chỉ put nếu có
    if (customer.getAvatar() != null) {
        values.put(COL_AVATAR, customer.getAvatar());
    }

    long result = -1;
    try {
        result = db.insert(TABLE_CUSTOMER, null, values);
    } catch (Exception e) {
        Log.e("DatabaseHelper", "Error inserting customer: " + e.getMessage());
    } finally {
        db.close();
    }

    return result;
}
    public boolean updateAccountPassword(int accountId, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String newPassword = new String();
        values.put("password", newPassword);

        int rowsAffected = 0;
        try {
            rowsAffected = db.update("account", values, "account_id = ?", new String[]{String.valueOf(accountId)});
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error updating password: " + e.getMessage());
        } finally {
            db.close();
        }

        return rowsAffected > 0; // true nếu cập nhật thành công
    }
    
}
