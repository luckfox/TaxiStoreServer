/**
 * 車輛檢驗資料表管理器（封裝 DataTables）
 */
class VIDataTableManager {
    /**
     * 建構子
     * @param {string} tableSelector - 表格 DOM selector
     * @param {object} callbacks - 各種事件的 callback，如 { onEdit, onDelete, onMessage }
     */
    constructor(tableSelector, callbacks = {}) {
        this.tableSelector = tableSelector;
        this.callbacks = callbacks;
        this.dataTable = null;
    }

    /**
     * 初始化 DataTable
     */
    init() {
        const self = this;  // 🤓 this 要先存起來避免 callback 裡面迷路

        this.dataTable = $(this.tableSelector).DataTable({
            processing: true,
            serverSide: true,
            searching: false,
            info: false,
            pageLength: 10,
            ajax: {
                url: 'situationServlet',
                type: 'GET',
                data: function (d) {
                    console.log("🔍 DataTables Ajax 發送數據：", d);
                    d.action = 'ajaxGetVehicleInspectionStatus';
                    d.year = $("#queryRangeLabel").attr("year");
                    d.season = $("#queryRangeLabel").attr("season");
                    d.companySeq = $("#queryCompanyLabel").attr("companySeq");
                    d.orderColumn = d.order[0].column;
                    d.orderDir = d.order[0].dir;

                    console.log(`d.year=${d.year} d.season=${d.season} d.companySeq=${d.companySeq} d.orderColumn=${d.orderColumn} d.orderDir=${d.orderDir}`);
                }
            },
            order: [[3, 'desc']],
            columns: [
                {data: 'carno', title: "車牌號碼", visible: true},
                {data: 'driverSeq', title: "司機序號", visible: false},
                {data: 'driverName', title: "駕駛人", visible: true},
                {
                    data: 'caryear', title: "出廠日期",
                    render: function (data) {
                        return formatDate(data);
                    }
                },
                {
                    data: 'companyName', title: "公司名稱", visible: false,
                    render: function (data) {
                        return data || "";
                    }
                },
                {
                    data: 'shingleDat', title: "掛牌日期",
                    render: function (data) {
                        return formatDate(data);
                    }
                },
                {
                    data: 'terminateDate', title: "繳銷日期",
                    render: function (data) {
                        return formatDate(data);
                    }
                },
                {
                    data: 'newRegistration', title: "新掛牌日期",
                    render: function (data) {
                        let text = formatDate(data);
                        return `<div class="single-ellipsis" title="${text}">${text}</div>`;
                    }
                },
                {
                    data: 'terminatedStatus', title: "已繳銷日期",
                    render: function (data) {
                        return formatDate(data);
                    }
                },
                {
                    data: null,
                    title: "操作",
                    render: function (data, type, row) {
                        return `
                            <button class="btn btn-sm btn-primary edit-btn" 
                                data-id="${row.driverSeq}" 
                                data-carno="${row.carno}" 
                                data-drivername="${row.driverName}" 
                                data-companyname="${row.companyName}" 
                                data-shingledat="${row.shingleDat}">
                                編輯
                            </button>
                            <button class="btn btn-sm btn-danger delete-btn" 
                                data-id="${row.driverSeq}" 
                                data-carno="${row.carno}" 
                                data-drivername="${row.driverName}" 
                                data-companyname="${row.companyName}" 
                                data-shingledat="${row.shingleDat}">
                                刪除
                            </button>
                            <button class="btn btn-sm btn-secondary message-btn" 
                                data-id="${row.driverSeq}" 
                                data-carno="${row.carno}" 
                                data-drivername="${row.driverName}" 
                                data-companyname="${row.companyName}" 
                                data-shingledat="${row.shingleDat}">
                                訊息
                            </button>`;
                    }
                }
            ],
            initComplete: function () {
                $(self.tableSelector + "_filter").empty();
                $(self.tableSelector + "_length").empty();
            }
        });

        // 綁定事件
        this.bindEvents();
    }

    /**
     * 綁定表格按鈕事件
     */
    bindEvents() {
        const self = this;

        $(this.tableSelector + " tbody").on('click', '.edit-btn', function () {
            const rowData = $(this).data();
            console.log('[edit-btn] 👨‍🔧 被點了：', rowData);
            if (typeof self.callbacks.onEdit === 'function')
            {
                self.callbacks.onEdit($(this),rowData);
            }
        });

        $(this.tableSelector + " tbody").on('click', '.delete-btn', function () {
            const rowData = $(this).data();
            console.log('[delete-btn] 🗑️ 被點了：', rowData);
            if (typeof self.callbacks.onDelete === 'function') {
                self.callbacks.onDelete(rowData);
            }
        });

        $(this.tableSelector + " tbody").on('click', '.message-btn', function () {
            const rowData = $(this).data();
            console.log('[message-btn] ✉️ 被點了：', rowData);
            if (typeof self.callbacks.onMessage === 'function') {
                self.callbacks.onMessage(rowData);
            }
        });

        // 重新繪製時加上黃色字
        $(this.tableSelector).on('draw.dt', function () {
            $(self.tableSelector + ' td').css('color', 'yellow');
        });
    }

    /**
     * 重新載入資料
     */
    reload() {
        this.dataTable.ajax.reload();
    }
}
