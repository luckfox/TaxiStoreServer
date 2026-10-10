## FAQ

#### 1。maven錯誤

* 錯誤項目：執行maven出現以下錯誤問題

    ```
    Unresolved plugin: 'org.apache.maven.plugins:maven-clean-plugin:2.5'
    Unresolved plugin: 'org.apache.maven.plugins:maven-resources-plugin:2.6'
    Unresolved plugin: 'org.apache.maven.plugins:maven-surefire-plugin:2.12.4'
    Unresolved plugin: 'org.apache.maven.plugins:maven-install-plugin:2.4'
    Unresolved plugin: 'org.apache.maven.plugins:maven-deploy-plugin:2.7'
    Unresolved plugin: 'org.apache.maven.plugins:maven-site-plugin:3.3'
    ```

    

* 解決方法：IntelliJ 中配置 Maven

 **檢查 Maven 設置**

1. **打開 Maven 配置**
   - 點擊 `File > Settings > Build, Execution, Deployment > Build Tools > Maven`。
2. **選擇 Maven 主目錄**
   - 如果使用 IntelliJ 內置 Maven，選擇 `Use Maven wrapper` 或 `Bundled Maven`.
   - 如果使用系統 Maven，選擇 `Use Custom` 並指定 Maven 主目錄（例如：`/usr/local/apache-maven-3.x`）。
3. **檢查本地倉庫路徑**
   - 默認本地倉庫路徑為：`~/.m2/repository`。
   - 如果有自定義本地倉庫，修改 `Local Repository` 為你的路徑。
4. **配置 JDK**
   - 確保選擇合適的 JDK（例如 Java 8 或 Java 11）。

#### 2。desktop.ini

* 說明：專案的路徑下各個目錄都出現desktop.ini。會造成maven在編譯發生錯誤。解決方法如下。

  * 在pom.xml內進行以下設置，則maven會對於src/main/resources進行忽略。

    ````xml
        <build>
            <resources>
                <resource>
                    <directory>src/main/resources</directory>
                    <excludes>
                        <exclude>**/desktop.ini</exclude>
                    </excludes>
                </resource>
            </resources>
        </build>
    ````

    

  * 在Intellij 進行設置。

    * 打開 IntelliJ IDEA，進入 **File > Settings > File Types**。
    * 在 **Ignore files and folders** 欄位中，添加 `desktop.ini`，使 IntelliJ 自動忽略此類文件。

#### 3。db 無法連上

* 說明：在取得Db Connection時候都會發生錯誤。Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)，但是加上Class.forName("com.mysql.jdbc.Driver")，便排除問題。

  ````java
          Class.forName("com.mysql.jdbc.Driver");
          try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
              System.out.println("Database connected successfully.");
          } catch (SQLException e) {
              System.err.println("Database connection failed: " + e.getMessage());
              e.printStackTrace();
              throw new RuntimeException("Database connection failed.", e);
          }
  ````

  

#### 4。JSP 除錯

* 說明：如何在JSP頁面中除錯。
  * 使用alert("show the message");
  * console.log("show the message"); ，並且在瀏覽器中(F12)，切換至主控台進行調試。

#### 5。$.getJSON

* 說明：

  * 專門用於處理伺服器返回的 JSON 數據。
  * jQuery 自動將返回的 JSON 字符串解析為 JavaScript 對象。

  #### 語法

  ```javascript
  $.getJSON(url, data, successCallback);
  ```

  #### 參數

  1. **`url`**（字串）
     請求的 URL，例如：`/getUserName`。

  2. **`data`**（物件或字串，可選）
     傳遞到伺服器的請求參數，會自動附加到 URL 的查詢字串中，例如：

     ```
     { userNo: "123" }
     ```

     最終形成：`/getUserName?userNo=123`。

  3. **`successCallback`**（函式，可選）
     當請求成功並返回有效 JSON 時調用的函式，格式如下：

     ```javascript
     function (data) {
       console.log(data);
     }
     ```

     其中 `data` 是已解析的 JSON 數據。

#### 6。servlet無法連上

* 原因：在jsp檔的.getJSON無法連到後台的servlet，後來發現TomCat所使用的java JRE版本，和專案所使用的java sdk版本不一致。

#### 7。properties 檔案為亂碼

*   **檢查文件編碼**
  1. **右鍵點擊 `.properties` 文件** → **`File Encoding`**。
  2. 確保編碼設置為 **`UTF-8`** 或你文件所使用的正確編碼（如 `ISO-8859-1`）。
  3. 如果不正確，將其更改為正確的編碼。

* **設置 IntelliJ 的默認編碼**
  1. 進入 **`File`** → **`Settings`** （macOS 使用 **`Preferences`**）。
  2. 導航至 **`Editor`** → **`File Encodings`**。
  3. 在 **`Project Encoding`** 和 **`Default Encoding for Properties Files`** 中設置為 **`UTF-8`**。
  4. 確保 **`Transparent native-to-ascii conversion`** 被啟用，這樣可以避免非 ASCII 字符亂碼。

* **手動轉換亂碼內容**
  如果文件原本是亂碼，你需要重新保存文件：	

	1.使用一個支持多種編碼的編輯器（如 Notepad++ 或 VS Code）打開文件。
	
	2.選擇正確的編碼並將文件重新保存為 **`UTF-8`** 編碼。
	
	3.在 IntelliJ 中重新加載該文件。

#### 8。 deployment source 'XXX' is not valid

原因：

````

Configuration Error: deployment source 'TaxiStoreServer' is not valid
[2025-01-13 09:56:45,954] Artifact TaxiStoreServer: Error during artifact deployment. See server log for details.
````

說明：應該是生成的artifact 檔名或位置和Tomcat設定檔的Deployment，請執行下列步驟。

* 在Intellij => File/File structure/artifact 先將.war檔案移除，再重新加入。並且重新Rebuild
* 在Intellij的Tomcat configure內的Deployment，將剛剛所建立的artifact 檔案加入。

### 開發

#### Bugs

* 在CreateCarDB，上下頁切換時，如果有盡如修改項目，則很容易發生錯誤(DB connection被關閉)

## SQL

* 由Db_A.table1 複製到Db_B

  ````
  CREATE TABLE b.table1 LIKE a.table1;
  
  INSERT INTO b.table1
  SELECT * FROM a.table1;
  ````

  

```
<div class="input-group col-md-3 mb-1">
    <label class="input-group-text">廠牌</label>
    <input type="text" class="form-control update-enabled carbrand-select" placeholder="廠牌" id="carBrandSC"
           value="${requestScope.FormCar.carBrandSC}" disabled="disabled" >
    <select class="input-group-text form-select update-enabled"
            id="carBrandName" disabled="disabled">
        <option selected>${requestScope.FormCar.carBrandName}</option>
        <option value="1">張育瑋</option>
        <option value="2">劉國泰</option>
        <option value="3">張黃阿甘</option>
    </select>
</div>

這部分請幫我做一些修正。
1。當使用者點選select按鈕時，則應該要以.getJSON向carServlet 的action=getCarBrand 取得資料，並將取得的資料放入此select之中。
2。請幫我補充servlet的部分。

以下為carBrand的資料結構。
CREATE TABLE `carbrand` (
  `CARBRANDID` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CARBRANDID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
```





```
$("select").change(function()
{
    var $current_elem = $(this);
    var $input_elem=$($current_elem.parent().find("input")[0]);

    //獲取選到的option
    var $selected_option=$($current_elem.find("option:selected")[0]);
    console.log("specialCode="+$selected_option.attr("sp"));
    $input_elem.val($selected_option.attr("sp"));

});
續上。當我select改變時，同時也會將所對應的carbrand.code，填入id=carBrandSC的input元件。
請幫我修改以上ˇ
```

請幫我設計一個簡單的Welcome頁面，這個頁面有個按鈕，按下之後，會向welcomeServlet發送action=getMessage。
而後台的welcomeServlet收到後，則會回傳一個歡迎訊息，回傳到Welcome頁面，並以更新。

