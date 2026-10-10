function companyInfo($module_container,modal1_preFix)
{
    let m_companyInfoPath = "/pages/layout/modals/companyInfo.html?_="+ new Date().getTime();
    let url=`companyServlet?action=ajaxGetCompany&serialNo=`+$("#companyseq").val();
    console.log(`#labelCompanyName=>\${url}`);
    $.getJSON(url, function(result)
    {
        if (!result.success)
        {
            //fail
            return ;
        }
        console.log(`m_companyInfoPath Path=\${m_companyInfoPath}`);
        $module_container.load(m_companyInfoPath, function ()
        {
            companyInfoModal_ID=replaceInnerIds($module_container,modal1_PreFix);
            //將從.getJSON取的到的資料，根據modal id映射到modal中。
            applyDataToModalFields(result.data, companyInfoModal_ID);
            $(companyInfoModal_ID).modal('show');
        });
    });
}