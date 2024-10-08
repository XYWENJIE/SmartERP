<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>劳动合同</title>
  <style>
    body {
      font-family: "fangsong";
      font-size: 15pt;
    }

    div {
      text-indent: 2em;
    }

    .content {
      margin: 60px 70px;
    }
    .row {
      --bs-gutter-x: 1.5rem;
      --bs-gutter-y: 0;
      display: flex;
      flex-wrap: wrap;
      margin-top: calc(-1 * var(--bs-gutter-y));
      margin-right: calc(-0.5 * var(--bs-gutter-x));
      margin-left: calc(-0.5 * var(--bs-gutter-x));
    }
    .col {
      flex: 1 0 0%;
    }

    .col-4 {
      flex: 0 0 auto;
      width: 33.33333333%;
    }

    .col-5 {
      flex: 0 0 auto;
      width: 41.66666667%;
    }

    .text-center {
      text-align: center !important;
    }

    .paragraph-underline {
      border-bottom: 1px solid black;
      padding-left: 10pt;
      padding-right: 10pt;
    }

    .text-decoration-underline {
      text-decoration: underline !important;
    }
    p {
      text-indent:2em;
    }
  </style>
</head>
<body>
<div class="content">
  <div style="page-break-after: always;">
    <div style="font-family: '黑体',serif;font-size: 36pt;text-align: center;padding-top: 120px;padding-bottom: 35px;">劳&nbsp;动&nbsp;合&nbsp;同</div>
    <div style="text-align: center;margin-bottom: 165px;font-size: 22pt">(通用)</div>
    <div>
      <div class="row" style="padding-bottom: 8px">
        <div class="col-4" style="width: 35%;text-indent: 0;">甲方（用人单位）：</div>
        <div class="col text-center " style="border-bottom: 1px solid black;">${team.teamName}</div>
      </div>
      <div class="row" style="padding-bottom: 8px">
        <div class="col-4" style="width: 35%;text-indent: 0;">
          乙方（劳 动 者）：
        </div>
        <div class="col text-center" style="border-bottom: 1px solid black;">
          ${personnel.realName}
        </div>
      </div>
      <div class="row">
        <div class="col-4" style="width: 35%;text-indent: 0;">签   订  日  期：</div>
        <div class="col text-center paragraph-underline">2024年08月28日</div>
      </div>
    </div>
  </div>

  <div style="page-break-after: always;">
    <div class="text-center" style="font-family: '黑体',serif;font-size: 18pt;padding-bottom: 18pt;">注 意 事 项</div>
    <div>一、本合同文本供用人单位与建立劳动关系的劳动者签订劳动合同时使用。</div>
    <div>二、用人单位应当与招用的劳动者自用工之日起一个月内依法订立书面劳动合同，并就劳动合同的内容协商一致。</div>
    <div>三、用人单位应当如实告知劳动者工作内容、工作条件、工作地点、职业危害、安全生产状况、劳动报酬以及劳动者要求了解的其他情况；用人单位有权了解劳动者与劳动合同直接相关的基本情况，劳动者应当如实说明。</div>
    <div>四、依法签订的劳动合同具有法律效力，双方应按照劳动合同的约定全面履行各自的义务。</div>
    <div>五、劳动合同应使用蓝、黑钢笔或签字笔填写，字迹清楚，文字简练、准确，不得涂改。确需涂改的，双方应在涂改处签字或盖章确认。</div>
    <div>六、签订劳动合同，用人单位应加盖公章，法定代表人（主要负责人）或委托代理人签字或盖章；劳动者应本人签字，不得由他人代签。劳动合同由双方各执一份，交劳动者的不得由用人单位代为保管。</div>
  </div>

  <div class="row" style="text-indent: 0;">
    <div style="text-indent: 0;">甲方（用人单位）：</div>
    <div class="col" style="border-bottom: 1px solid black;text-indent: 0;">${team.teamName}</div>
  </div>
  <div style="text-indent: 0;">统一社会信用代码：                                         </div>
  <div style="text-indent: 0;">法定代表人（主要负责人）或委托代理人：                     </div>
  <div style="text-indent: 0;">注 册 地：                                                 </div>
  <div style="text-indent: 0;">经 营 地：                                                 </div>
  <div style="padding-bottom: 20pt;text-indent: 0;">联系电话：                                                 </div>
  <div class="row">
    <div style="text-indent: 0;">乙方（劳动者）：</div>
    <div class="col" style="border-bottom: 1px solid black;text-indent: 0;">${personnel.realName}</div>
  </div>
  <div style="text-indent: 0;">居民身份证号码：                                           </div>
  <div style="text-indent: 0;">（或其他有效证件名称         证件号：   　　　           ）</div>
  <div style="text-indent: 0;">户籍地址：                                                   </div>
  <div style="text-indent: 0;">经常居住地（通讯地址）：                                    </div>
  <div style="padding-bottom: 20pt;" class="row">
    <div style="text-indent: 0;">联系电话：</div>
    <div class="col" style="border-bottom: 1px solid black;">${personnel.phone}</div>
  </div>
  <div>根据《中华人民共和国劳动法》《中华人民共和国劳动合同法》等法律法规政策规定,甲乙双方遵循合法、公平、平等自愿、协商一致、诚实信用的原则订立本合同。</div>
  <div style="font-family: '黑体';">一、劳动合同期限</div>
  <div><strong>第一条</strong> 甲乙双方自用工之日起建立劳动关系，双方约定按下列第<span class="paragraph-underline">1</span>种方式确定劳动合同期限：</div>
  <div>1.固定期限：自     年    月    日起至    年    月   日止，其中，试用期从用工之日起至     年    月    日止。</div>
  <div>2.无固定期限：自     年    月    日起至依法解除、终止劳动合同时止，其中，试用期从用工之日起至     年    月    日止。</div>
  <div>3.以完成一定工作任务为期限：自     年    月    日起至                                                工作任务完成时止。甲方应当以书面形式通知乙方工作任务完成。</div>
  <div style="font-family: '黑体';">二、工作内容和工作地点</div>
  <div>第二条  乙方工作岗位是                      ，岗位职责为                                               。乙方的工作地点为                                    。</div>
  <div>乙方应爱岗敬业、诚实守信，保守甲方商业秘密，遵守甲方依法制定的劳动规章制度，认真履行岗位职责，按时保质完成工作任务。乙方违反劳动纪律，甲方可依据依法制定的劳动规章制度给予相应处理。</div>
  <div style="font-family: '黑体';">三、工作时间和休息休假</div>
  <div>第三条 根据乙方工作岗位的特点，甲方安排乙方执行以下第<span class="paragraph-underline">1</span>种工时制度：</div>
  <div >1.标准工时工作制。每日工作时间不超过8小时，每周工作时间不超过40小时。由于生产经营需要，经依法协商后可以延长工作时间，一般每日不得超过1小时，特殊原因每日不得超过3小时，每月不得超过36小时。甲方不得强迫或者变相强迫乙方加班加点。</div>
  <div >2.依法实行以      为周期的综合计算工时工作制。综合计算周期内的总实际工作时间不应超过总法定标准工作时间。甲方应采取适当方式保障乙方的休息休假权利。</div>
  <div >3.依法实行不定时工作制。甲方应采取适当方式保障乙方的休息休假权利。</div>
  <div >第四条 甲方安排乙方加班的，应依法安排补休或支付加班工资。</div>
  <div >第五条 乙方依法享有法定节假日、带薪年休假、婚丧假、产假等假期。</div>
  <div style="font-family: '黑体';">四、劳动报酬</div>
  <div >第六条 甲方采用以下第<span class="paragraph-underline">1</span>种方式向乙方以货币形式支付工资，于每月<span class="paragraph-underline">15</span>日前足额支付：</div>
  <div >1.月工资<span class="paragraph-underline">2800</span>元。</div>
  <div >2.计件工资。计件单价为                              ，甲方应合理制定劳动定额，保证乙方在提供正常劳动情况下，获得合理的劳动报酬。</div>
  <div>3.基本工资和绩效工资相结合的工资分配办法，乙方月基本工资        元，绩效工资计发办法为                          。</div>
  <div>4.双方约定的其他方式                                 。</div>
  <div>第七条 乙方在试用期期间的工资计发标准为  或         元。</div>
  <div>第八条 甲方应合理调整乙方的工资待遇。乙方从甲方获得的工资依法承担的个人所得税由甲方从其工资中代扣代缴。</div>
  <div style="font-family: '黑体';">五、社会保险和福利待遇</div>
  <div>第九条 甲乙双方依法参加社会保险，甲方为乙方办理有关社会保险手续，并承担相应社会保险义务，乙方应当缴纳的社会保险费由甲方从乙方的工资中代扣代缴。</div>
  <div>第十条 甲方依法执行国家有关福利待遇的规定。</div>
  <div>第十一条 乙方因工负伤或患职业病的待遇按国家有关规定执行。乙方患病或非因工负伤的，有关待遇按国家有关规定和甲方依法制定的有关规章制度执行。</div>
  <div style="font-family: '黑体';">六、职业培训和劳动保护</div>
  <div>第十二条 甲方应对乙方进行工作岗位所必需的培训。乙方应主动学习，积极参加甲方组织的培训，提高职业技能。</div>
  <div>第十三条 甲方应当严格执行劳动安全卫生相关法律法规规定，落实国家关于女职工、未成年工的特殊保护规定，建立健全劳动安全卫生制度，对乙方进行劳动安全卫生教育和操作规程培训，为乙方提供必要的安全防护设施和劳动保护用品，努力改善劳动条件，减少职业危害。乙方从事接触职业病危害作业的，甲方应依法告知乙方工作过程中可能产生的职业病危害及其后果，提供职业病防护措施，在乙方上岗前、在岗期间和离岗时对乙方进行职业健康检查。</div>
  <div>第十四条 乙方应当严格遵守安全操作规程，不违章作业。乙方对甲方管理人员违章指挥、强令冒险作业，有权拒绝执行。</div>
  <div style="font-family: '黑体';">七、劳动合同的变更、解除、终止</div>
  <div>第十五条 甲乙双方应当依法变更劳动合同，并采取书面形式。</div>
  <div>第十六条 甲乙双方解除或终止本合同，应当按照法律法规规定执行。</div>
  <div>第十七条 甲乙双方解除终止本合同的，乙方应当配合甲方办理工作交接手续。甲方依法应向乙方支付经济补偿的，在办结工作交接时支付。</div>
  <div>第十八条 甲方应当在解除或终止本合同时，为乙方出具解除或者终止劳动合同的证明，并在十五日内为乙方办理档案和社会保险关系转移手续。</div>
  <div style="font-family: '黑体';">八、双方约定事项</div>
  <div>第十九条 乙方工作涉及甲方商业秘密和与知识产权相关的保密事项的，甲方可以与乙方依法协商约定保守商业秘密或竞业限制的事项，并签订保守商业秘密协议或竞业限制协议。</div>
  <div>第二十条 甲方出资对乙方进行专业技术培训，要求与乙方约定服务期的，应当征得乙方同意，并签订协议，明确双方权利义务。</div>
  <div>第二十一条  双方约定的其它事项：
    。</div>
  <div style="font-family: '黑体';">九、劳动争议处理</div>
  <div>第二十二条 甲乙双方因本合同发生劳动争议时，可以按照法律法规的规定，进行协商、申请调解或仲裁。对仲裁裁决不服的，可以依法向有管辖权的人民法院提起诉讼。</div>
  <div style="font-family: '黑体';">十、其他</div>
  <div>第二十三条 本合同中记载的乙方联系电话、通讯地址为劳动合同期内通知相关事项和送达书面文书的联系方式、送达地址。如发生变化，乙方应当及时告知甲方。</div>
  <div>第二十四条 双方确认：均已详细阅读并理解本合同内容,清楚各自的权利、义务。本合同未尽事宜，按照有关法律法规和政策规定执行。</div>
  <div>第二十五条 本合同双方各执一份，自双方签字（盖章）之日起生效，双方应严格遵照执行。</div>
  <table style="width: 100%;padding-top: 30pt;">
    <tr>
      <td>
        <div>甲方（盖章）</div>
        <div>法定代表人（主要负责人）</div>
        <div>或委托代理人（签字或盖章）</div>
        <div>    年   月   日</div>
      </td>
      <td>
        <div>乙方（签字）</div>
        <div>&nbsp;</div>
        <div>&nbsp;</div>
        <div>      年   月   日</div>
      </td>
    </tr>
  </table>
</div>

</body>
</html>