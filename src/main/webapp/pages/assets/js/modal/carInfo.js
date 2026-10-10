/*
    param module_container modal掛載點
    param modalHtmlPath modal html所在位置
    param servletUrl servlet?action所在位置
 */
function show_Modal($module_container,modalHtmlPath,servletUrl)
{
    const modal1_PreFix=new Date().getTime()+"_";
    const m_modalPath = modalHtmlPath+"?_="+ new Date().getTime();

    $.getJSON(servletUrl, function(result)
    {
        if (!result.success)
        {
            return ;
        }
        //console.log(`m_carInfoPath Path=\${m_carInfoPath}`);
        $module_container.load(m_modalPath, function (){
            let modal_ID=replaceInnerIds($module_container,modal1_PreFix);
            //將從.getJSON取的到的資料，根據modal id映射到modal中。
            applyDataToModalFields(result.data, modal_ID);
            $(modal_ID).modal('show');
        });
    });
}