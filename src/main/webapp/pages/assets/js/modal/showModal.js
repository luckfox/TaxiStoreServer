

/*
    param module_container modal掛載點
    param htmlPath modal html所在位置
    param url servlet?action所在位置
 */
function show_Modal($module_container,modalHtmlPath,servletUrl)
{
    let modal1_PreFix=new Date().getTime()+"_";
    let m_modalPath = modalHtmlPath+"?_="+ new Date().getTime();
    console.log(`container=${$module_container} modalPath=${modalHtmlPath} servlet=${servletUrl}`);
    // console.log('modal Container 存在嗎?', $(module_container).length);
    $.getJSON(servletUrl, function(result)
    {
        if (!result.success)  return ;
        $module_container.load(m_modalPath, function ()
        {
            let modal_ID=replaceInnerIds($module_container,modal1_PreFix);
            //將從.getJSON取的到的資料，根據modal id映射到modal中。
            applyDataToModalFields(result.data, modal_ID);
            // console.log('modal root 存在嗎?', $(modal_ID).length);
            $(modal_ID).modal('show');
        });
    });
}