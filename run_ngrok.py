import subprocess

# 設定 ngrok Auth Token
auth_token = "2sJZbeoln5I13KyVZdTaYGUokGx_7vKTSKxquMDySwryiDNLf"

def run_command(command):
    """執行 shell 命令並輸出結果"""
    process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    stdout, stderr = process.communicate()
    if stdout:
        print(stdout)
    if stderr:
        print("Error:", stderr)

# 1. 設定 ngrok 授權碼
print("Setting ngrok auth token...")
run_command(f"ngrok config add-authtoken {auth_token}")

# 2. 啟動 ngrok HTTP 轉發
print("Starting ngrok on port 8080...")
run_command("ngrok http 8080")
