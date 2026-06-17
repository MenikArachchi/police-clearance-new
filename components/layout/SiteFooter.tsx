export default function SiteFooter() {
  const year = new Date().getFullYear();
  return (
    <>
      <div style={{ clear: 'both' }}></div>
      <div className="es-powered-by">Powered by Sri Lanka Police</div>
      <div style={{ clear: 'both' }}></div>
      <div id="es-footer" className="es-footer">
        <div>&nbsp;</div>
        <div>
          <div>
            <span className="footer-label">
              Police Clearance Certificate System &nbsp;&copy;&nbsp;{year} Sri Lanka Police. All rights reserved.
            </span>
          </div>
          <div style={{ textAlign: 'center' }}>
            <span className="footer-label">
              Powered by Information and Communication Technology Agency of Sri Lanka (ICTA).
            </span>
          </div>
        </div>
      </div>
    </>
  );
}
