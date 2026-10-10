/*
* 這是一個dropdown的映射函式，當從指定的serlver(apiUrl?action)取得相對應的資料後，根據dataMapper格式排定後
* 產生一個row，並且放置在dropdown的list內。另外也提供了OnSelect事件，當使用者，點選list中間的某個row後，
* */

function handleAutoCompleteDropdown(e, inputField, dropdownList, options) {
    const {
        apiUrl,
        action,
        keyName,
        keyLength,
        dataMapper,  // 傳入一個 function：怎麼把 item 映射成 <li>
        onSelect     // 點選 dropdown-item 後怎麼處理
    } = options;
    console.log(`apiUrl=`+apiUrl+` action=`+action+` keyName=`+keyName);

    const theTarget = inputField.val().trim();
    if (theTarget.length < keyLength) return;
    if (e.type === 'keydown' && e.key !== 'Enter') return;
    console.log(`apiUrl=${apiUrl} action=${action}`);
    const query = {};
    query['action'] = action;
    query[keyName] = theTarget;

    $.getJSON(apiUrl, query, function (result)
    {
        if (!result.success) {
            dropdownList.removeClass('show');
            return;
        }
        console.log(`connection to ${apiUrl}${action} success!`);
        const items = result.data.map(dataMapper).join('');

        const offset = inputField.position();
        dropdownList.css({
            top: inputField.outerHeight(),
            left: offset.left
        }).html(items).addClass('show');

        dropdownList.find('.dropdown-item').click(function () {
            onSelect($(this));
            dropdownList.removeClass('show');
        });

        // 點擊外部收起 dropdown
        setTimeout(() => {
            $(document).one('click', function (event) {
                if (!dropdownList.is(event.target) &&
                    dropdownList.has(event.target).length === 0 &&
                    !inputField.is(event.target)) {
                    dropdownList.removeClass('show');
                }
            });
        }, 0);
    });
}


/**
 * 根據後端回傳的資料項目 (item)，依據指定的 visibleTextKeys（支援 '*' 表示全部塞入），
 * 加上 optionsArgu（支援 '*'，額外塞的），以及 excludeKeys（我不想要的），
 * 動態產生 dropdown 中的 <li><button>。
 *
 * @param {Object} item - 從後端取得的資料物件，每個 key 對應欄位值。
 * @param {Array<string>} visibleTextKeys - 要顯示在 dropdown 項目中的欄位 key。
 * @param {Array<string>} optionsArgu - 額外強制加入 data-* 屬性的欄位 key，支援 '*'。
 * @param {Array<string>} [excludeKeys=[]] - 要排除的欄位 key，不要出現在 data-* 裡。
 * @param {string} [containerSelector='#root'] - 欲查找輸入欄位的容器，預設為 #root。
 * @returns {string} HTML 字串，可插入 autocomplete dropdown。
 */
function autoMapItemToList(item, visibleTextKeys = [], optionsArgu = [], excludeKeys = [], containerSelector = '#root') {

    // 🟢 容器處理：支援字串或 jQuery 物件
    const $container =
        (typeof containerSelector === 'string')
            ? $(containerSelector)
            : containerSelector; // 如果本來就是 jQuery 物件就直接用

    const $fields = $container.find('input[id], select[id], textarea[id]');
    const dataSet = new Set(); // 用來記錄已經塞進去的 key，避免重複
    let dataAttrs = '';


    // 🔍 先印出 item 所有資料
    // console.log(`[autoMapItemToList] item 內總共有 ${Object.keys(item).length} 個欄位：`);
    // Object.entries(item).forEach(([key, value]) => {
    //     console.log(`  - ${key}:`, value);
    // });

    // 組成 visibleText（畫面 button 要顯示的文字內容，不包含 * 和排除）
    const visibleTextFinalKeys = visibleTextKeys.filter(key => !excludeKeys.includes(key));
    //console.log(`[autoMapItemToList] ⬇️ visibleTextKeys（實際顯示）：`);
    // visibleTextFinalKeys.forEach(key => {
    //     console.log(`  - ${key}:`, item[key]);
    // });

    // Step 1️⃣：處理畫面上有的 input/select/textarea 元素（優先）
    $fields.each(function () {
        const id = $(this).attr('id');
        if (!excludeKeys.includes(id) && item[id] !== undefined && !dataSet.has(id)) {
            dataAttrs += ` data-${id}="${item[id]}"`;
            dataSet.add(id);
        }
    });

    // Step 2️⃣：處理 optionsArgu 如果含 '*'，則塞所有欄位
    const includeAllFromOptions = optionsArgu.includes('*');
    if (includeAllFromOptions) {
        for (const [key, value] of Object.entries(item)) {
            if (!excludeKeys.includes(key) && !dataSet.has(key)) {
                dataAttrs += ` data-${key}="${value}"`;
                dataSet.add(key);
            }
        }
    }

    // Step 3️⃣：處理 optionsArgu 中指定的欄位（除了 '*' 之外）
    optionsArgu.forEach(key => {
        if (
            key !== '*' &&
            item[key] !== undefined &&
            !excludeKeys.includes(key) &&
            !dataSet.has(key)
        ) {
            dataAttrs += ` data-${key}="${item[key]}"`;
            dataSet.add(key);
        }
    });

    // Step 4️⃣：產生 visibleText 顯示文字
    const visibleText = visibleTextFinalKeys.map(key => item[key] || '').join('--');
    console.log(`[autoMapItemToList] ✅ 產生的 data-* 屬性：`, dataAttrs);

    return `<li>
        <button type="button" class="dropdown-item"${dataAttrs}>
            ${visibleText}
        </button>
    </li>`;
}
