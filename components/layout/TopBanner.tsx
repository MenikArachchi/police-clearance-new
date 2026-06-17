export default function TopBanner() {
  return (
    <div id="es-header">
      <div className="top_banner">
        <div style={{ position: 'absolute', height: '100px', width: '100%' }}>
          <table style={{ position: 'absolute', margin: '5px 0 0 5px' }}>
            <tbody>
              <tr>
                <td>
                  <svg
                    style={{ width: '72px', height: '72px', fill: '#000066' }}
                    viewBox="0 0 24 24"
                    aria-label="Sri Lanka Police"
                  >
                    <path d="M12 1L3 5v6c0 5.55 3.84 10.74 9 12 5.16-1.26 9-6.45 9-12V5l-9-4z" />
                    <path fill="#fff" d="M10 17l-4-4 1.41-1.41L10 14.17l6.59-6.59L18 9l-8 8z" />
                  </svg>
                </td>
                <td style={{ paddingLeft: '10px' }}>
                  <span className="logo">Sri Lanka Police</span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div style={{ clear: 'both' }}></div>
    </div>
  );
}
