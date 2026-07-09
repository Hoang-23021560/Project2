package com.javaweb.utils;

import java.security.Key;
import java.util.List;
import java.util.Map;
//Nhiệm vụ của hàm này là: Lấy một giá trị từ trong Map<String, Object> dựa vào key,
// sau đó tự động ép kiểu (cast) về đúng kiểu dữ liệu (Long, Integer, String) mà người dùng mong muốn,
// đồng thời xử lý chống lỗi dữ liệu trống (chuỗi rỗng "" hoặc null).
public class MapUtil {
//    <T> và T (Generic): Đây là kiểu dữ liệu đại diện (tham số hóa). Nó có nghĩa là: "Hàm này có thể trả về bất kỳ kiểu dữ liệu nào
//    (Long, Integer, String, BuildingRequest...), tùy thuộc vào việc lúc gọi hàm bạn truyền vào kiểu gì".
    public static <T> T getObject(Map<String, Object> params,String key, Class<T> tClass){
    // String key lấy ra key trong cái map
        Object obj = params.getOrDefault(key,null);
        //Bản chất : tìm trong Map xem có cái key nào trùng không nếu có già lấy ra ,không có giá trị mặc đinh = null
        if(obj != null){
            if(tClass.getTypeName().equals("java.lang.Long")){
                obj = !obj.toString().equals("") ? Long.valueOf(obj.toString()) : null;

            }
            else if(tClass.getTypeName().equals("java.lang.Integer")){
                obj = !obj.toString().equals("") ? Integer.valueOf(obj.toString()) : null;

            }
            else if(tClass.getTypeName().equals("java.lang.Double")){
                obj = !obj.toString().equals("") ? Double.valueOf(obj.toString()) : null;

            }
            else{
                obj = obj.toString();

            }
            return tClass.cast(obj);// ép kieu lan cuoi;
        }
        return null;

    }
}
