import React, { ReactNode } from 'react'

export default function Header({ children }: { children: ReactNode }) {
    return (
        <div className="bg-charcoal-blue">
            {children}
        </div>
    )
}
