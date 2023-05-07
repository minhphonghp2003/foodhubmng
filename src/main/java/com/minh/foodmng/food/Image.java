package com.minh.foodmng.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
@Data
@Table(name = "images")
public class Image  {
    private String getImgLink(String link) throws IOException {
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("/home/phong/Downloads/FirebasePrivKey.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("staticfile-9a793.appspot.com", "foodorder/product/316167096-food1.jpeg"));
        URL url = storage.signUrl(blob, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        return url.toString();
    }
    @Id
    private int id;
    private String link;
    @JsonIgnore
    @ManyToMany(mappedBy = "images")
    private List<Food>foods;
    public String getLink() {
        try{

            return getImgLink(link);
        }catch (Exception e){
            return "";
        }
    }

}
