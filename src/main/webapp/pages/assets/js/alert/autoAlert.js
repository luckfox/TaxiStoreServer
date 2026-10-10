// autoAlert.js
//export
class AutoAlert {
    constructor(options = {}) {
        this.options = options;

        // 第一次建立時，動態注入 CSS
        if (!document.getElementById("auto-alert-style")) {
            const style = document.createElement("style");
            style.id = "auto-alert-style";
            style.textContent = `
                .custom-alert {
                        position: fixed;
                        top: 300px;
                        left: 50%;
                        transform: translateX(-50%);
                        background-color: #ffecb3;
                        color: #000;
                        padding: 15px 30px;
                        border: 2px solid #fbc02d;
                        border-radius: 8px;
                        box-shadow: 0 4px 10px rgba(0,0,0,0.2);
                        font-family: "Comic Sans MS", cursive, sans-serif;
                        font-size: 16px;
                        z-index: 9999;
                        opacity: 0;
                        transition: opacity 0.5s ease-in-out;
                        pointer-events: none;
                        }

                .custom-alert.show {
                        opacity: 1;
                        }
                `;
            document.head.appendChild(style);
        }
    }
    show(message, durationInSeconds = 3) {
        const alertBox = document.createElement("div");
        alertBox.className = "custom-alert";
        alertBox.textContent = message;

        document.body.appendChild(alertBox);
        requestAnimationFrame(() => alertBox.classList.add("show"));

        setTimeout(() => {
            alertBox.classList.remove("show");
            setTimeout(() => alertBox.remove(), 500);
        }, durationInSeconds * 1000);
    }
}
window.AutoAlert = AutoAlert;