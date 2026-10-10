/*
    以下是提供給inputMappings作為
    從後台收到的資料，填入dropwdown中每個line的內容值。
 */
function mapCarItemToList(item) {
    const template = `
        <li>
            <button type="button" class="dropdown-item"
                data-carseq="%s"
                data-carNo="%s"
                data-ownerName="%s"
                data-ownerSC="%s"            
                data-ownerCompanyName="%s"
                data-ownerCompanySC="%s"
                data-str_caryear="%s"
                data-mechanicss="%s"
                data-carlineno="%s"
                data-pipeamount="%s"
                data-carcolor="%s">

                %s--%s
            </button>
        </li>`;
    return sprintf(template, item.carseq,
                             item.carno,
                             item.ownerName,
                             item.ownerSC,
                             item.ownerCompanyName,
                             item.ownerCompanySC,
                             item.str_caryear,
                             item.mechanicss,
                             item.carlineno,
                             item.pipeamount,
                             item.carcolor,
                             item.carno,
                             item.ownerName);
}
function mapSituationToList(item) {
    const template = `
             <li>
                <button type="button" class="dropdown-item"
                    data-situationId="%s"
                    data-carNo="%s"
                    data-driverName="%s"
                    data-createDate="%s"
                    data-portable="%s"
                    data-companyName="%s"
                    data-companyseq="%s"
                    data-specialCode="%s"
                    data-recvDeposit="%s"
                    data-receipts="%s">
                    %s-%s(%s)
                </button>
            </li>`;
    return sprintf(template, item.situationId, item.carNo, item.driverName, item.createDate,item.portable,
        item.companyName,item.companyname,item.specialCode,item.recvDeposit,JSON.stringify(item.receipts),
        item.carNo,item.driverName,item.specialCode);
}
/*
    由.GetJSON取得driver資料後，放到
 */
function mapDriverToList(item) {
    const template=`
        <li>
            <button type="button" class="dropdown-item"
                data-driverseq="%s"
                data-drivername="%s"
                data-id="%s"
                data-portable="%s"
                data-specialcode="%s"
                data-str_birthday="%s"
                data-commadd="%s"
                data-commtel="%s"
                data-householda="%s"
                data-householdt="%s"
                data-pfine="%s"
                data-pfineper="%s"
                data-pfineday="%s"
                data-carno="%s"
                data-ownerName="%s"
                data-remark="%s"
                data-ownerSC="%s"
                data-str_indate="%s"
                data-str_outdate="%s"
                data-guarantor="%s"
                data-relation="%s"
                data-guarantorid="%s"
                data-guarantortel="%s"
                data-guarantoradd="%s"
                >                
                %s--%s
            </button>
        </li>`;
    return sprintf(template,item.driverseq,
                            item.drivername,
                            item.id,
                            item.portable,
                            item.specialcode,
                            item.str_birthday,
                            item.commadd,
                            item.commtel,
                            item.householda,
                            item.householdt,
                            item.pfine,
                            item.pfineper,
                            item.pfineday,
                            item.carno,
                            item.ownerName,
                            item.remark,
                            item.ownerSC,
                            item.str_indatem,
                            item.str_outdate,
                            item.guarantor,
                            item.relation,
                            item.guarantorid,
                            item.guarantortel,
                            item.guarantoradd,
                            item.drivername,
                            item.specialcode);
}


