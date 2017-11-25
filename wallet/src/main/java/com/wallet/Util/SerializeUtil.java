package com.wallet.Util;

import java.io.*;

/**
 * Created by Administrator on 2017/8/15.
 */
public class SerializeUtil {
    /**
     * 序列化
     * @param py
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static String Serialize(String py) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(py);
        //deserialize object, get new object newpair
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        py = (String) ois.readObject();
        return py;
    }


}
