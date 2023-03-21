package com.luckyone.ocrserver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class OcrServerApplicationTests  {

	@Test
	void testMultiThread() {
		for (int i = 0; i < 15; i++) {
			new Thread(new OcrRunner()).start();
		}
	}

	@Test
	void testOnce() {
		new OcrRunner().run();
	}

	class OcrRunner implements Runnable{
		@Override
		public void run() {
			OkHttpClient client = new OkHttpClient()
					.newBuilder()
					.connectTimeout(30, TimeUnit.SECONDS)
					.callTimeout(120, TimeUnit.SECONDS)
					.pingInterval(5, TimeUnit.SECONDS)
					.readTimeout(60, TimeUnit.SECONDS)
					.writeTimeout(60, TimeUnit.SECONDS)
					.build();
			byte[] bytes = FileUtil.readBytes(new File("./res/ch.jpg"));
//        MediaType mediaType = MediaType.parse("text/plain");
			RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("file","ch.jpg",
							RequestBody.create(MediaType.parse("application/octet-stream"),
									bytes))
					.addFormDataPart("fileName","ch.jpg")
					.build();
			Request request = new Request.Builder()
					.url("http:127.0.0.1:8080/image/parse")
					.method("POST", body)
					.build();
			Response response = null;
			System.out.println(new Date() + "--send-------");
			try {
				response = client.newCall(request).execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(new Date() + "--response-------");
			System.out.println(IoUtil.read(response.body().byteStream()));
		}
	}

}
