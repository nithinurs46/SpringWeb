package com.springweb.query;

public interface AppQueries {

	public static final String privQuery = "SELECT UNIQUE (b.message_number) MESSAGE_NUMBER,b.message_file_size, message_format_version MESSAGE_FORMAT_VERSION,sender_institution,"
			+ "transmission_date TRANSMISDATE, b.e2b_language, b.icsr_message_number,b.MDN_MESSAGE MDNMsg,b.MDN_DATE MDN_DATE, b.e2b_message_type E2BMSGTYPE,d.doctype_name "
			+ "FROM e2b_aer_import a, e2b_message_queue b,e2b_custom_doctype d WHERE NVL(UPPER(b.icsr_message_number),' ') LIKE  UPPER('%') ESCAPE '\\' "
			+ "AND NVL(b.e2b_message_type,' ') LIKE '01' AND a.message_number = b.message_number AND b.message_format_version = d.doctype_code AND "
			+ "b.trans_queue_flag = '01' AND b.status = '02' AND  NVL(b.ROUTE_THROUGH_AGX, ' ') <> '1'  AND    b.message_format_version = '05' ";

	public static final String headerQuerySql = "SELECT eai.message_number, eai.safetyreportid,a.APPROVAL_STATUS,a.composite_version_no,eai.icsr_file_size,eai.report_ack_code,eai.e2b_approved,"
			+ "eai.e2b_aer_owner_unit,eai.e2b_authority,eai.international_version_flag,eai.authority_numb,eai.company_numb,eai.unique_numb_found,"
			+ "eai.duplicate_aer_no,eai.duplicate_aer_approved,eai.rep_seq,eai.status,eai.aer_receipt_date,eai.version_no,eai.local_version_no,"
			+ "eai.report_class,eai.nullified,eai.medicinal_product,eai.prev_unapproved_ver,eai.import_status,eai.import_report_class,eai.aer_doc_flag ,"
			+ "eai.IS_DOC_ATTACHED,eai.ARIS_AER_NO,eai.WORKFLOW_INSTANCE_ID,eai.serious,eai.death,eai.life_threatening,eai.followup_type,eai.seq_document_file,"
			+ "eai.doc_id,eai.R3_ATTACHMENT_FLAG , eai.ERROR_MESSAGE  FROM e2b_aer_import eai left outer join (select APPROVAL_STATUS,composite_version_no,aer_no "
			+ "from  aer  where aer_last='1') a on eai.DUPLICATE_AER_NO = a.AER_NO where eai.message_number = ? AND "
			+ "eai.status IN ('02', '03', '04', '05', '06','50', '51','98','99') ORDER BY aer_receipt_date DESC";
}
