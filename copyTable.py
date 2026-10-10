# 需要安裝以下套件
# pip install tqdm 
# pip install pymysql
#
#

import pymysql
from tqdm import tqdm
import getpass

DB_HOST = "localhost"
DB_PORT = 3309
DB_USER = "root"
SOURCE_DB = "dazhongyuan"
TARGET_DB = "taxistore"

tables = [
    "car","situation","driver","fine","recventry",
    "recvform","company","payentry","payform"
]

def main():
    # 執行時問密碼
    db_pass = getpass.getpass("請輸入資料庫密碼（支援中文/特殊字元）: ")

    conn = pymysql.connect(
        host=DB_HOST,
        user=DB_USER,
        password=db_pass,
        port=DB_PORT,
        autocommit=True
    )
    cursor = conn.cursor()

    print(f"切換到資料庫 {TARGET_DB}")
    cursor.execute(f"USE {TARGET_DB}")
    cursor.execute("SET FOREIGN_KEY_CHECKS=0;")

    for table in tqdm(tables, desc="複製進度", unit="table"):
        print(f"\n處理表格：{table}")
        try:
            sql = f"DROP TABLE IF EXISTS {table}"
            cursor.execute(sql)

            sql = f"CREATE TABLE {table} LIKE {SOURCE_DB}.{table}"
            cursor.execute(sql)

            sql = f"INSERT INTO {table} SELECT * FROM {SOURCE_DB}.{table}"
            cursor.execute(sql)

        except Exception as e:
            print(f"表格 {table} 發生錯誤！SQL: {sql}")
            print(f"錯誤訊息: {e}")

    cursor.execute("SET FOREIGN_KEY_CHECKS=1;")
    cursor.close()
    conn.close()
    print("\n✅ 全部複製完成！")

if __name__ == "__main__":
    main()
