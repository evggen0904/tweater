<#import "parts/common.ftl" as c>
<@c.page>
    <#if user??>
        <h5>Hello, ${user.username}</h5>
    <#else>
        <h5>Hello, guest</h5>
    </#if>
<div>This is just a Twitter clone</div>
</@c.page>

