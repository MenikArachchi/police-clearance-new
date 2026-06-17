import clsx from 'clsx';
import {
  ApplicationReviewStatusLabel,
  ApplicationClearanceStatusLabel,
  ApplicationStatusCheckLabel,
} from '@/lib/constants';

type BadgeVariant = 'review' | 'clearance' | 'citizen';

interface StatusBadgeProps {
  status: string;
  variant?: BadgeVariant;
}

const reviewColors: Record<string, string> = {
  TS: 'bg-gray-100 text-gray-700',
  NW: 'bg-blue-100 text-blue-700',
  RV: 'bg-yellow-100 text-yellow-700',
  VF: 'bg-green-100 text-green-700',
  RC: 'bg-orange-100 text-orange-700',
  RM: 'bg-purple-100 text-purple-700',
  RJ: 'bg-red-100 text-red-700',
  TM: 'bg-gray-100 text-gray-500',
};

const clearanceColors: Record<string, string> = {
  PN: 'bg-yellow-100 text-yellow-700',
  IS: 'bg-green-100 text-green-700',
  NC: 'bg-blue-100 text-blue-700',
  CP: 'bg-teal-100 text-teal-700',
  DC: 'bg-indigo-100 text-indigo-700',
  BL: 'bg-red-100 text-red-700',
  RJ: 'bg-red-100 text-red-700',
  EI: 'bg-emerald-100 text-emerald-700',
  GC: 'bg-green-100 text-green-800',
  RC: 'bg-orange-100 text-orange-700',
};

const citizenColors: Record<string, string> = {
  SUB: 'bg-blue-100 text-blue-700',
  ACC: 'bg-teal-100 text-teal-700',
  VER: 'bg-yellow-100 text-yellow-700',
  REJ: 'bg-red-100 text-red-700',
  DEL: 'bg-orange-100 text-orange-700',
  POS: 'bg-green-100 text-green-700',
};

export default function StatusBadge({ status, variant = 'review' }: StatusBadgeProps) {
  const colorMap =
    variant === 'clearance' ? clearanceColors : variant === 'citizen' ? citizenColors : reviewColors;

  const labelMap =
    variant === 'clearance'
      ? ApplicationClearanceStatusLabel
      : variant === 'citizen'
      ? ApplicationStatusCheckLabel
      : ApplicationReviewStatusLabel;

  const color = colorMap[status] ?? 'bg-gray-100 text-gray-600';
  const label = labelMap[status] ?? status;

  return (
    <span className={clsx('inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium', color)}>
      {label}
    </span>
  );
}
