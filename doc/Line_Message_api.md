### **1 建立 Webhook URL  Server**

#### 1.1 建立 Webhook Server

* 為何要建立WebHook Server？當有人想與你的 LINE 官方帳號互動（例如發送訊息、點擊按鈕），LINE 需要有一個方式 **通知你的伺服器**，讓你的應用程式知道發生了什麼事，反之，我也能夠使我的應用程式發送訊息，透過此WebHook Server轉送到相對應的使用者，這就是 **Webhook** 的用途。

Webhook **本質上就是一個 HTTP 伺服器**，它負責接收來自 LINE 的事件通知。這些通知會包含：

- **用戶發送的訊息**
- **用戶加入/退出聊天室**
- **按鈕或選單點擊事件**

**如果你的伺服器不對外開放，LINE 會無法發送這些事件通知。**

#### 1.2 ngrok

​	ngrok是一個類似tomcat的小型http伺服器，而我們另外撰寫的webHook就如同掛皆在此伺服器上的應用，也就是當ngrok伺服器收到http的POST請求。則會轉發到掛在此伺服器上的所有應用，如果有符合者，則接收後進行處理。

##### 1.2.1 安裝與設定

在 `https://ngrok.com/` 註冊後可以取得authtoken，下載軟體後再終端機進行如下設置。

```bash
ngrok config add-authtoken 2sJZbeoln5I13KyVZdTaYGUokGx_7vKTSKxquMDySwryiDNLf
```

進行運作，以下為Listen port為8080

````
ngrok http 8080
````

執行後會出現以下畫面。

![](G:\我的雲端硬碟\大中原\系統開發\LINE Message api\image\30-01-2025-05-25-31.png)

並且透過以下的網址進行觀察。

````
http://127.0.0.1:4040/inspect/http
````

![](G:\我的雲端硬碟\大中原\15。系統開發\LINE Message api\image\30-01-2025-11-18-36.png)



如此，可以確定訊息已經傳送至`ngrok`。



#### 1.3 執行webHook

開啟LineBotWebHook專案，透過mvn執行之。

````
mvn spring-boot:run
````

#### 1.4 確認webHook成功運行

為了方便測試，LineBotWebHook保留了Get request的能力，因此只要使用 http://localhost:8080/webHook 就能確定是否正確運行。
````
http://localhost:8080/webHook
````

如果有回應，則表示正常運作。

### 2.Message Api設定

#### 2.1 建立一個provider

在 LINE Developer下建立一個provider，並且在Message API下可以獲取Channel ID和Channel secret，並且把先前的WebHook的網頁路徑貼上。注意，ngrok在每次運作時所產生的webHook路徑皆會有所不同。所以得要再次更新。

![](G:\我的雲端硬碟\大中原\15。系統開發\LINE Message api\image\31-01-2025-12-29-34.png)

````
https://developers.line.biz/console/channel/2006833972/messaging-api
````







### 3.測試

#### 3.1 在powerShell下發送以下訊息。

````bash
$headers = @{
    "Content-Type" = "application/json"
}

$body = '{
    "destination": "Uxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "events": [
        {
            "type": "message",
            "message": {
                "type": "text",
                "id": "1234567890",
                "text": "Hello Webhook!"
            },
            "timestamp": 1620000000000,
            "source": {
                "type": "user",
                "userId": "Uxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
            },
            "replyToken": "abcdefg1234567890",
            "mode": "active"
        }
    ]
}'

Invoke-RestMethod -Uri "https://5b38-111-184-27-33.ngrok-free.app/webhook" -Method Post -Headers $headers -Body $body

````

若結果正確，則表示透過自身APP傳送到ngrok正確無誤。
