function computeImageHash(file)
{
    const reader = new FileReader();
    reader.onload = function (e)
    {
        crypto.subtle.digest('SHA-256', e.target.result).then((hashBuffer) =>
        {
            const hashArray = Array.from(new Uint8Array(hashBuffer));
            const hashHex = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
            processAdd(hashHex, URL.createObjectURL(source));
        });
    };
    reader.readAsArrayBuffer(source);
}