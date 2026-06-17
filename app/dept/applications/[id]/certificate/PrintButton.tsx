'use client';

export default function PrintButton() {
  return (
    <button
      onClick={() => window.print()}
      style={{
        display: 'block',
        margin: '20px auto',
        padding: '8px 24px',
        background: '#337ab7',
        color: '#fff',
        border: 'none',
        borderRadius: '4px',
        fontSize: '14px',
        cursor: 'pointer',
      }}
      className="no-print"
    >
      Print Certificate
    </button>
  );
}
